package com.ktconexiones.flow.server.business.services;

import com.ktconexiones.flow.server.business.util.Distance;
import com.ktconexiones.flow.server.domain.Image;
import com.ktconexiones.flow.server.persistence.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service 
public class ImageServiceImpl implements ImageService
{
    @Autowired
    private ImageDao dao;

    @Override
    public List<Image> getImages(double latitude, double longitude, int radius )
    {
        Distance.Point current = new Distance.Point( latitude, longitude );
        List<Image> all = dao.getAll();
        List<Image> result = new ArrayList<Image>();

        for( Image img : all )
        {
            Distance.Point tmp = new Distance.Point( img.getLatitude(), img.getLongitude() );

            if( Distance.getDistance( current, tmp ) < radius )
                result.add( img );
        }


        return result;
    }

    
    @Override
    public void create(Image image)
    {
        if( image != null )
            dao.insert( image );
    }


    @Override
    public Image getById(int id) {
        return dao.getById( id );
    }
}


/*
*
* http://201.166.131.163/2010/08/13/1281694835862.jpg
* http://201.166.131.163/2010/08/13/1281694870141.jpg
* http://201.166.131.163/2010/08/13/1281694873508.jpg
* */