package com.ktconexiones.flow.android.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.app.FlowApplication;
import com.ktconexiones.flow.android.app.Keys;
import com.ktconexiones.flow.android.app.Util;

import java.io.FileNotFoundException;

import static com.ktconexiones.flow.android.app.Keys.Logging.UPLOAD;


public class ShareActivity extends Activity implements View.OnClickListener
{
    private NotificationManager notificationManager;
    private LocationManager locationManager;
    private Bitmap bmCurrent;           // needed to for memory mgmt ... recycle() method is invoked on this one on the onStart() method
    //http://stackoverflow.com/questions/1949066/java-lang-outofmemoryerror-bitmap-size-exceeds-vm-budget-android

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView( R.layout.upload );

        this.notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Button upload = (Button) findViewById( R.id.upload_submit );
        Button cancel = (Button) findViewById( R.id.upload_cancel );

        upload.setOnClickListener( this );
        cancel.setOnClickListener( this );
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d( UPLOAD, "Do start" );
        housekeeping();
        this.locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, FlowApplication.getInstance().getFlowLocationListener() );

        ImageView iv = (ImageView) super.findViewById( R.id.upload_image );
        Uri uri = super.getIntent().getExtras().getParcelable( Intent.EXTRA_STREAM );
        Log.d( UPLOAD, "Uri fetched: " + uri );

        try
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            Bitmap bmTmp = BitmapFactory.decodeStream( super.getContentResolver().openInputStream( uri ), null, options);

            //iv.setImageURI( uri );
            iv.setImageBitmap( bmTmp );
            iv.setAdjustViewBounds(true);
            iv.setVisibility( View.VISIBLE );
            iv.refreshDrawableState();          // refresh the state, in order that the new image will be rendered

            this.bmCurrent = bmTmp;
        }
        catch (FileNotFoundException e)
        {
            Log.e( UPLOAD, "Major Upsie", e );
        }
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d( UPLOAD, "on stop invoked" );
        this.locationManager.removeUpdates( FlowApplication.getInstance().getFlowLocationListener() );
        housekeeping();
    }

    public void onClick(View view)
    {
        //ctx.moveTaskToBack(true);
        
        if( view.getId() == R.id.upload_submit )
        {
            if( FlowApplication.getInstance().getLocation() == null )
            {
                Util.showGpsAlert( this );
            }
            else
            {
                int notificationId = (int)System.currentTimeMillis();
                Uri uri = super.getIntent().getExtras().getParcelable( Intent.EXTRA_STREAM );
                Log.d( UPLOAD, "Do upload " + notificationId + ", " + uri );

                PendingIntent pending = PendingIntent.getActivity( this.getBaseContext(), 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT );

                Notification n = new Notification( android.R.drawable.stat_sys_upload, getString( R.string.common_uploading ), System.currentTimeMillis() );
                n.flags = Notification.FLAG_NO_CLEAR;
                n.setLatestEventInfo(
                        getApplicationContext(),
                        getString( R.string.app_name ) + " " + getString( R.string.common_upload ),
                        getString( R.string.common_uploading ) + " " + notificationId,
                        pending
                );


                notificationManager.notify( "n-"+notificationId, notificationId , n );

                // start uploading thread
                Intent intent = new Intent();
                intent.setAction( Keys.Upload.ACTION );
                intent.putExtra( Keys.Upload.NOTIFICATION_ID, notificationId );
                intent.putExtra( Keys.Upload.URI, uri );

                super.startService( intent );
                Toast.makeText( this, getString( R.string.common_uploading ), Toast.LENGTH_SHORT ).show();
                super.finish();
            }
        }
        else
        {
            // finish this activity
            super.finish();
            Log.d( UPLOAD, "Do cancel" );
        }

    }

    private void housekeeping()
    {
        // this to avoid memory leaks
        Log.d( UPLOAD, "Cleaning up the current bitmap" );

        if( this.bmCurrent != null )
        {
            this.bmCurrent.recycle();
            System.gc();
            this.bmCurrent = null;
        }
    }
    
}

