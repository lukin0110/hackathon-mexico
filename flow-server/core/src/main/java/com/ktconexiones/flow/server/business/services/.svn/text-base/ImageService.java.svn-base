package com.ktconexiones.flow.server.business.services;

import com.ktconexiones.flow.server.domain.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService
{
    /**
     * Returns a List of images all images which where captured within a certain radios of the given point.
     * The point is represented by the latitude & longitude parameters 
     *
     * @param latitude double value
     * @param longitude double value
     * @param radius int value in 'meters'
     * @return a List of matching images
     * */
    public List<Image> getImages( double latitude, double longitude, int radius );

    public void create( Image image );

    public Image getById( int id );
}
