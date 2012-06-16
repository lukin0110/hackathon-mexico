package com.ktconexiones.flow.server.persistence;

import com.ktconexiones.flow.server.domain.Image;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

@SuppressWarnings("unchecked")
public class SqlMapImageDao extends SqlMapClientDaoSupport implements ImageDao
{

    @Override
    public void insert(Image image)
    {
        getSqlMapClientTemplate().insert( "Images.insert", image );
    }

    @Override
    public List<Image> getAll()
    {
        return (List<Image>) getSqlMapClientTemplate().queryForList( "Images.all" );
    }

    @Override
    public Image getById(int id)
    {
        return (Image) getSqlMapClientTemplate().queryForObject( "Images.getById", id );
    }

}
