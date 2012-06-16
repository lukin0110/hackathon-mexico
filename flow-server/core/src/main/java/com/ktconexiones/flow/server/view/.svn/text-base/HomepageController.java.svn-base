package com.ktconexiones.flow.server.view;

import com.ktconexiones.flow.server.domain.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomepageController
{
    @Autowired
    private Configuration config;
    private Logger log = Logger.getLogger( getClass() );

    @RequestMapping( value="/index.html" )
    public String index( HttpServletRequest request )
    {
        request.setAttribute( "server", request.getServerName() );
        request.setAttribute( "config", config );
        log.debug( "Enter the world of the home page" );
        return "index";
    }

}

