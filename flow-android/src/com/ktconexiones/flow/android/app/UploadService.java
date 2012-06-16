package com.ktconexiones.flow.android.app;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.io.HttpMultipartRequest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.ktconexiones.flow.android.app.Keys.Logging;
import static com.ktconexiones.flow.android.app.Keys.Upload;


public class UploadService extends IntentService
{
    private NotificationManager manager;

    public UploadService()
    {
        super( "UploadServiceWorker" );
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        Log.d( Logging.UPLOAD_SERVICE, "Service started: startId = " + startId );
    }

    /*
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {*/

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.d( Logging.UPLOAD_SERVICE, "Service onHandleIntent invoked" );
        int notificationId = intent.getExtras().getInt( Upload.NOTIFICATION_ID );
        int notificationMessage;

        try
        {
            Uri uri = intent.getExtras().getParcelable( Upload.URI );
            Log.d( Logging.UPLOAD_SERVICE, "NotiId= " + notificationId + ", uri= " + uri );

            // 1. fetching some meta data
            Cursor cursor = MediaStore.Images.Media.query( super.getContentResolver(), uri, new String[]{
                    MediaStore.Images.ImageColumns.MIME_TYPE,
                    MediaStore.Images.ImageColumns.DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.TITLE
            } );

            cursor.moveToFirst();
            String mime = cursor.getString(0);
            String name = cursor.getString(1);
            String title = cursor.getString(2);


            // 2. Fetching users location
            //Location loc = FlowApplication.getInstance().getLastLocation();
            Location loc = FlowApplication.getInstance().getFlowLocationListener().getLocation();

            // if location == null we'll try a 'best possible' location provider
            if( loc == null )
            {
                Log.d( Logging.UPLOAD_SERVICE, "Location == null -> trying to fetch the best lastKnownLocation" );
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                String best = lm.getBestProvider( new Criteria(), false );
                Log.d( Logging.UPLOAD_SERVICE, "Best location provider = " + best );
                loc = lm.getLastKnownLocation( best );
            }

            String lat, lon;

            if( loc != null )
            {
                lat = "" + loc.getLatitude();
                lon = ""+ loc.getLongitude();
            }
            else
            {
                lat = "bummer";
                lon = "bummer";
            }

            Map<String, String> params = new HashMap<String, String>();
            params.put( "latitude", "" + lat );
            params.put( "longitude", "" + lon );
            Log.d( Logging.UPLOAD_SERVICE, "latitude=" + lat + ", logitude=" + lon );

            // 3. Now we'll post the image to the server
            InputStream is = super.getContentResolver().openInputStream( uri );

            HttpMultipartRequest hmr = new HttpMultipartRequest( Config.SERVER + "/rest/upload/form.html", params, "file", name, mime, is );
            String result = hmr.send();
            //String result = "void";

            Log.d( Logging.UPLOAD_SERVICE, "mime="+mime+",name="+name+",title="+title + ", http result=" + result );
            notificationMessage = R.string.common_uploaded;
        }
        catch (Exception e)
        {
            Log.e( Logging.UPLOAD_SERVICE, "Putain, merde !!", e  );
            notificationMessage = R.string.common_upload_failed;
        }

        PendingIntent pending = PendingIntent.getActivity( this.getBaseContext(), 0, new Intent(), PendingIntent.FLAG_ONE_SHOT );
        Notification n = new Notification( R.drawable.iconflow, getString(notificationMessage), System.currentTimeMillis() );
        n.setLatestEventInfo( getApplicationContext(), getString(R.string.app_name), getString(notificationMessage), pending );
        n.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        manager.notify( "n-"+notificationId, notificationId, n );
    }

}
