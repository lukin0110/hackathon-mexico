package com.ktconexiones.flow.android.app;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import static com.ktconexiones.flow.android.app.Keys.Logging.LOCATION_LISTENER;

public class FlowLocationListener implements LocationListener
{
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void onLocationChanged(Location location) {
        Log.d( LOCATION_LISTENER, "GPS: location changed = " + location );

        if( location != null )
            this.location = location;
    }

    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d( LOCATION_LISTENER, "GPS: status changed" );
    }

    public void onProviderEnabled(String s) {
        Log.d( LOCATION_LISTENER, "GPS: on enable" );
    }

    public void onProviderDisabled(String s) {
        Log.d( LOCATION_LISTENER, "GPS: on disable" );
    }


}
