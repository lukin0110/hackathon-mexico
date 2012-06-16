package com.ktconexiones.flow.server.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Image
{
    private double latitude, longitude, altitude;
    private int id;
    private String name, mimeType;
    private Date created, updated;
    private String path;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public String getThumbPath()
    {
        return getPath() + "/thumb_" + name;
    }

    public String getMidPath()
    {
        return getPath() + "/mid_" + name;
    }

    public String getPath()
    {
        if( path == null )
            this.path =  "/" + new SimpleDateFormat( "yyyy/MM/dd" ).format( created );

        return path;
    }
}
