package com.ktconexiones.flow.server.business.services;

import com.ktconexiones.flow.server.business.util.IO;
import com.ktconexiones.flow.server.domain.Configuration;
import com.ktconexiones.flow.server.domain.Location;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

@Service
public class LocationServiceImpl implements LocationService
{
    @Autowired
    private Configuration config; 
    private Logger log = Logger.getLogger( getClass() );
    private String uri = "{0}/location/getLocation?appkey={1}&phoneNumber={2}&requestedAccuracy=20000&acceptableAccuracy=65000&maximumAge=80000&responseTime=100000&tolerance=DELAY_TOLERANT";

    /*http://cpmex.openapiservice.com/v1.0/location/getLocation?
    appkey=849df0f3c5b306ecf29a147f&phoneNumber=19175550001
    &requestedAccuracy=20000&acceptableAccuracy=65000
    &maximumAge=80000&responseTime=100000&tolerance=DELAY_TOLERANT*/

    @Override
    public Location getLocation(String phoneNumber)
    {
        InputStream is = null;

        try
        {
            /* Success response

            <ns:getLocationResponse>
            <ns:return>SU100</ns:return>
            <ns:reason>Success</ns:reason>
            <ns:phoneNumber>19175550001</ns:phoneNumber>
            <ns:location>
            <ns:latitude>40.75889</ns:latitude>
            <ns:longitude>-74.00493</ns:longitude>
            <ns:altitude>0.0</ns:altitude>
            <ns:accuracy>12056</ns:accuracy>
            <ns:timestamp>1281730313491</ns:timestamp>
            </ns:location>
            </ns:getLocationResponse>}>*/

            /* Error response

            <ns:getLocationResponse xmlns:ns="http://service.las.alu.com/xsd">
            <ns:return>RE402</ns:return>
            <ns:reason>Wrong key for application 1</ns:reason>
            <ns:requestId>R82962</ns:requestId>
            <ns:phoneNumber>19175550001</ns:phoneNumber>
            </ns:getLocationResponse>*/

            SAXReader saxReader = new SAXReader();
            is = getLocationAsInputStream( phoneNumber );
            Document doc = saxReader.read( is );

            Node result = (Node) doc.selectNodes( "/ns:getLocationResponse/ns:return" ).get(0);

            // we have a success
            if( "SU100".equals( result.getText() ) )
            {
                Location location = new Location();

                double lat = Double.parseDouble( ((Node)doc.selectNodes( "/ns:getLocationResponse/ns:location/ns:latitude" ).get(0)).getText() );
                location.setLatitude( lat );

                double lon = Double.parseDouble( ((Node)doc.selectNodes( "/ns:getLocationResponse/ns:location/ns:longitude" ).get(0)).getText() );
                location.setLongitude( lon );

                double alt = Double.parseDouble( ((Node)doc.selectNodes( "/ns:getLocationResponse/ns:location/ns:altitude" ).get(0)).getText() );
                location.setAltitude( alt );

                int acc = Integer.parseInt( ((Node)doc.selectNodes( "/ns:getLocationResponse/ns:location/ns:accuracy" ).get(0)).getText() );
                location.setAccurancy( acc );

                long time = Long.parseLong( ((Node)doc.selectNodes( "/ns:getLocationResponse/ns:location/ns:timestamp" ).get(0)).getText() );
                location.setTimestamp( time );

                return location;
            }

        }
        catch(Exception e)
        {
            log.error( "", e );
        }
        finally
        {
            try{ is.close(); } catch(Exception e){ log.error( "Upsie, but it didn't break anything", e ); }
        }
        
        return null;
    }


    private String getLocationAsString( String phoneNumber ) throws IOException
    {
        InputStream is = null;

        try
        {
            is = getLocationAsInputStream( phoneNumber );
            return IO.getInputStreamAsString( is );
        }
        finally
        {
            try{ is.close(); } catch(Exception e){ log.error( "Upsie, but it didn't break anything", e ); }
        }
    }



    private InputStream getLocationAsInputStream( String phoneNumber ) throws IOException
    {
        HttpURLConnection conn = null;

        try
        {
            String url = MessageFormat.format( uri, config.getOpenApiServer(), config.getOpenApiAppKey(), phoneNumber );

            System.out.println( url );

            conn = (HttpURLConnection) new URL( url ).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod( "GET" );

            conn.connect();

            return conn.getInputStream();
        }
        finally
        {
            //try{ conn.disconnect(); } catch(Exception e){ log.error( "Upsie, but it didn't break anything", e ); }
        }
    }

    public static void main(String[] args) throws IOException
    {
        Configuration config = new Configuration();
        config.setOpenApiServer( "http://cpmex.openapiservice.com/v1.0" );
        config.setOpenApiAppKey( "849df0f3c5b306ecf29a147f" );

        LocationServiceImpl service = new LocationServiceImpl();
        service.config = config;

        System.out.println( service.getLocation( "19175550001" ) + "\n");
        System.out.println( service.getLocation( "19175550002" ) + "\n");
        System.out.println( service.getLocation( "19175550016" ) + "\n");
        System.out.println( service.getLocation( "19175550010" ) + "\n");
        System.out.println( service.getLocation( "19175550005" ) + "\n");
        System.out.println( service.getLocation( "19175550013" ) + "\n");
        
        //System.out.println( service.getLocation( "19175550001" ) );
        //System.out.println( service.getLocationAsString( "19175550001" ) );
    }


}
