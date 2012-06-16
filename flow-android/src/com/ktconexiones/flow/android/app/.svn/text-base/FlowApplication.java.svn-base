package com.ktconexiones.flow.android.app;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import java.util.Date;

import static com.ktconexiones.flow.android.app.Keys.Logging.APP;

/*
* Construcutor will be invoked at startup (cfr AndroidManifest.xml)
* With getInstance() we'll have static access to this instance throughout our app.
* We can store/share statefull stuff here (which needs to be shared by different Activities)
* */
public class FlowApplication extends Application
{
    private static FlowApplication instance;

    public static FlowApplication getInstance()
    {
        return instance;
    }

    private Date started;
    private FlowLocationListener flowLocationListener;

    public FlowApplication()
    {
        super();
        started = new Date();
        flowLocationListener = new FlowLocationListener();

        if( instance == null )
            instance = this;
        else
            Log.e( APP, "Constructor invoked twice?" );
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i( APP, "App started " + started + ", sdk: " + Build.VERSION.SDK );
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        Log.i( APP, "App terminated on " + new Date() );
    }

    public Date getStarted() {
        return started;
    }

    public FlowLocationListener getFlowLocationListener() {
        return flowLocationListener;
    }

    public Location getLocation()
    {
        Location loc = getFlowLocationListener().getLocation();

        // if location == null we'll try a 'best possible' location provider
        if( loc == null )
        {
            Log.d( Keys.Logging.UPLOAD_SERVICE, "Location == null -> trying to fetch the best lastKnownLocation" );
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String best = lm.getBestProvider( new Criteria(), false );
            Log.d( Keys.Logging.UPLOAD_SERVICE, "Best location provider = " + best );
            loc = lm.getLastKnownLocation( best );
        }

        return loc;
    }

}



