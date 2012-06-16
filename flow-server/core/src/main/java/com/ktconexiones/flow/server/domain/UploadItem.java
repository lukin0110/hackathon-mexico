package com.ktconexiones.flow.server.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadItem
{
    private double latitude, longitude;
    private CommonsMultipartFile fileData;

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

    public CommonsMultipartFile getFile(){
        return fileData;
    }

    public void setFile(CommonsMultipartFile fileData){
        this.fileData = fileData;
    }

}
