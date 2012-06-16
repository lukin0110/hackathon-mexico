package com.ktconexiones.flow.server.business.util;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IO
{
    private static Logger LOG = Logger.getLogger( IO.class );

    public static String getInputStreamAsString( InputStream is ) throws IOException
    {
        ByteArrayOutputStream bos = null;

        try
        {
             bos = new ByteArrayOutputStream();

            int ch;

            while ((ch = is.read()) != -1)
                bos.write(ch);

            return new String( bos.toByteArray() );
        }
        finally {
            try{ bos.close(); } catch(Exception e){ LOG.error( "Could not close outputstream", e ); }
        }
    }

}
