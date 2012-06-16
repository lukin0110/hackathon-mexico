package com.ktconexiones.flow.android.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.activity.tabs.BananaActivity;
import com.ktconexiones.flow.android.activity.tabs.FlowActivity;
import com.ktconexiones.flow.android.activity.tabs.FriendsActivity;
import com.ktconexiones.flow.android.app.FlowApplication;
import com.ktconexiones.flow.android.app.Util;

import static com.ktconexiones.flow.android.app.Keys.Logging.FLOW;

public class HomeActivity extends TabActivity
{
    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        setContentView( R.layout.home );

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        // Initialize a TabSpec for each tab and add it to the TabHost
        intent = new Intent().setClass(this, FlowActivity.class);
        spec = tabHost.newTabSpec( "flow" )
                .setIndicator( getString(R.string.home_tab_flow), res.getDrawable( android.R.drawable.ic_menu_gallery ))
                .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, FriendsActivity.class);
        spec = tabHost.newTabSpec( "friends" )
                .setIndicator( getString(R.string.home_tab_friends), res.getDrawable( android.R.drawable.ic_menu_mapmode ))
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, BananaActivity.class);
        spec = tabHost.newTabSpec( "banana" )
                .setIndicator( getString(R.string.home_tab_banana), res.getDrawable( android.R.drawable.ic_menu_info_details ))
                .setContent(intent);
        tabHost.addTab(spec);

        // set first tab active
        tabHost.setCurrentTab(0);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        this.locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, FlowApplication.getInstance().getFlowLocationListener() );
        //Toast.makeText( this, getString( R.string.common_welcome ), Toast.LENGTH_SHORT ).show();
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d( FLOW, "on stop invoked" );
        this.locationManager.removeUpdates( FlowApplication.getInstance().getFlowLocationListener() );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return Util.bindMenu( this, menu );
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return Util.menuItemSelected( this, item );
    }


    
}

