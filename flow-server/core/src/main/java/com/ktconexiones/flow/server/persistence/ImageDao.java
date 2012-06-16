package com.ktconexiones.flow.server.persistence;

import com.ktconexiones.flow.server.domain.Image;

import java.util.List;

public interface ImageDao
{
    public void insert( Image image );
    public List<Image> getAll();
    public Image getById( int id );

}
