package com.ktconexiones.flow.server.business.services;

import com.ktconexiones.flow.server.domain.Location;
import org.springframework.stereotype.Service;

@Service 
public interface LocationService
{
    public Location getLocation( String phoneNumber );
}
