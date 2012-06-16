package com.ktconexiones.flow.server.domain;

public class Location
{
    private double latitude, longitude, altitude;
    private long timestamp;
    private int accurancy;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getAccurancy() {
        return accurancy;
    }

    public void setAccurancy(int accurancy) {
        this.accurancy = accurancy;
    }

    @Override
    public String toString() {
        return "[latitude="+latitude+", longitude="+longitude+", altitude="+altitude+", timestamp="+timestamp+", accurancy=" + accurancy + "]";
    }
}
