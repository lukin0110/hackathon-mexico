package com.ktconexiones.flow.server.view;

import com.ktconexiones.flow.server.business.JsonView;
import com.ktconexiones.flow.server.business.services.ImageService;
import com.ktconexiones.flow.server.business.services.LocationService;
import com.ktconexiones.flow.server.domain.Configuration;
import com.ktconexiones.flow.server.domain.Image;
import com.ktconexiones.flow.server.domain.Location;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping( "/rest/list" )
public class ListController
{
    public static final int DEFAULT_MATCHING_RADIUS = 100; // 100meters

    private Logger log = Logger.getLogger( getClass() );

    @Autowired private LocationService locationService;
    @Autowired private ImageService imageService;
    @Autowired private Configuration config;


    @RequestMapping( "/gps/{lat}/{lon}.html" )
    public ModelAndView getGpsList(
            @PathVariable( "lat" ) String lat,
            @PathVariable( "lon" ) String lon,
            @RequestParam( value="out", required=false, defaultValue="html") String output,
            @RequestParam( value="radius", required=false, defaultValue=""+DEFAULT_MATCHING_RADIUS ) int radius )
    {
        ModelAndView mav = getModelAndView( output );
        log.debug( "GPS list requested: lat=" + lat + ", lon=" + lon );

        try
        {
            mav.addObject( "status", "ok" );
            mav.addObject( "lat", lat );
            mav.addObject( "lon", lon );

            double latitude = Double.parseDouble( lat );
            double longitude = Double.parseDouble( lon );

            bindImages( mav, output, latitude, longitude, radius );
        }
        catch (Exception e)
        {
            mav.addObject( "status", "nok" );
            mav.addObject( "reason", e.getMessage() );
            log.error( "", e );
        }

        return mav;
    }


    @RequestMapping( "/phone/{phone}.html" )
    public ModelAndView getPhoneList(
            @PathVariable( "phone" ) String phone, 
            @RequestParam( value="out", required=false, defaultValue="html" ) String output,
            @RequestParam( value="radius", required=false, defaultValue=""+DEFAULT_MATCHING_RADIUS ) int radius )
    {
        ModelAndView mav = getModelAndView( output );
        log.debug( "PHONE list requested: phone=" + phone );

        try
        {
            Location loc = locationService.getLocation( phone );

            if( loc != null )
            {
                mav.addObject( "status", "ok" );
                mav.addObject( "lat", loc.getLatitude() );
                mav.addObject( "lon", loc.getLongitude() );

                bindImages( mav, output, loc.getLatitude(), loc.getLongitude(), radius );
            }
        }
        catch (Exception e)
        {
            mav.addObject( "status", "nok" );
            mav.addObject( "reason", e.getMessage() );
            log.error( "", e );
        }

        return mav;
    }


    @RequestMapping( "/photo/{id}.html" )
    public ModelAndView getSinglePhoto( @PathVariable("id") int id )
    {
        ModelAndView mav = new ModelAndView("single");
        Image img = imageService.getById( id );

        mav.addObject( "image", img );
        mav.addObject( "config", config );

        return mav;
    }


    private ModelAndView getModelAndView( String output )
    {
        if( "json".equals(output))
            return new ModelAndView( new JsonView() );

        // default html view
        return new ModelAndView( "list" );
    }


    private void bindImages( ModelAndView mav, String output, double latitude, double longitude, int radius )
    {
        List<Image> list = imageService.getImages( latitude, longitude, radius );

        if( "json".equals(output) )
        {
            JSONArray arr = new JSONArray();

            for( Image i : list )
                arr.put( config.getStaticRoot() + i.getPath() );

            mav.addObject( "images", arr );
        }
        else
        {
            mav.addObject( "images", list );
            mav.addObject( "config", config );
        }
    }



}



