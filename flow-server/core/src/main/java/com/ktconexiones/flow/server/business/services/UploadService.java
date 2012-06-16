package com.ktconexiones.flow.server.business.services;

import com.ktconexiones.flow.server.domain.UploadItem;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UploadService
{
    public void save( UploadItem ui ) throws IOException;

}
