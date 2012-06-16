package com.ktconexiones.flow.android.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.activity.PreferencesActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Maarten
 * Date: Aug 13, 2010
 * Time: 2:45:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util
{
    public static String getStringFromInputStream( InputStream is ) throws IOException
    {
        ByteArrayOutputStream bos = null;

        try
        {
             bos = new ByteArrayOutputStream();

            int ch;

            while ((ch = is.read()) != -1)
                bos.write(ch);

            return new String( bos.toByteArray() );
        }
        finally {
            try{ bos.close(); } catch(Exception e){ Log.e( Keys.Logging.APP, "Could not close outputstream", e ); }
        }
    }

    
    public static boolean bindMenu( Activity ctx, Menu menu )
    {
        MenuInflater mi = ctx.getMenuInflater();
        mi.inflate( R.menu.main , menu );
        return true;
    }


    public static boolean menuItemSelected( Activity ctx, MenuItem item )
    {
        switch( item.getItemId() )
        {
            case R.id.menu_upload:
                // launch built-in photo gallery
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                ctx.startActivity( intent );
                return true;

            case R.id.menu_preferences:
                ctx.startActivity( new Intent( ctx, PreferencesActivity.class ) );
                return true;
        }

        return false;
    }

    public static void showGpsAlert( Activity ctx )
    {
        AlertDialog alert = new AlertDialog.Builder(ctx).create();
        alert.setTitle( ctx.getString(R.string.alert_gps_title) );
        alert.setMessage( ctx.getString(R.string.alert_gps_msg) );
        alert.setButton( ctx.getString(R.string.alert_gps_btn), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {}
        } );

        alert.show();
    }

}
