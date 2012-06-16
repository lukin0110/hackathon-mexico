package com.ktconexiones.flow.server.business;

import org.json.JSONObject;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View {

    public String getContentType() {
        return "text/javascript";
    }

    public void render( Map map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ) throws Exception
    {
        JSONObject json = new JSONObject();

        if( map != null ){

            for( Object o : map.entrySet() )
            {
                Map.Entry me = (Map.Entry) o;

                // spring adds this property and we don't wont this in the JSON result
                if( !"org.springframework.validation.BindingResult.images".equals( me.getKey() ) )
                    json.put( (String) me.getKey(), me.getValue() );
            }

        }

        bindHeaders( httpServletResponse );

        PrintWriter writer = httpServletResponse.getWriter();
        writer.write( json.toString() );
    }

    private void bindHeaders( HttpServletResponse httpServletResponse )
    {
        //yeah!!! most important day in history :)
        httpServletResponse.setHeader( "Expires", "Sun, 12 June 1983 12:00:00 GMT" );
        httpServletResponse.setHeader( "Content-Type", "text/javascript" );
        //httpServletResponse.setHeader( "Content-Type", "text/javascript; charset=UTF-8" );

        // Set standard HTTP/1.1 no-cache headers.
        httpServletResponse.setHeader( "Cache-Control", "no-store, no-cache,must-revalidate" );

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        httpServletResponse.addHeader( "Cache-Control", "post-check=0, pre-check=0" );
    }

}