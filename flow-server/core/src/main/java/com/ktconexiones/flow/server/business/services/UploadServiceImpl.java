package com.ktconexiones.flow.server.business.services;

import com.ktconexiones.flow.server.domain.Configuration;
import com.ktconexiones.flow.server.domain.Image;
import com.ktconexiones.flow.server.domain.UploadItem;
import com.ktconexiones.flow.server.persistence.ImageDao;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService
{
    @Autowired
    private Configuration config;
    
    @Autowired
    private ImageDao imageDao;

    private Logger log = Logger.getLogger( getClass() );

    @Override
    public void save(UploadItem ui) throws IOException
    {
        log.debug( "saving file : " + config );

        InputStream inputStream = ui.getFile().getInputStream();
        int buffLength = 512;
        byte[] buffer = new byte[buffLength];
        int bytesRead = inputStream.read( buffer, 0, buffLength );

        Date now = new Date();
        File target = getValidTarget( ui.getFile().getOriginalFilename(), now );
        FileOutputStream fos = new FileOutputStream( target );

        while( bytesRead > 0 )
        {
            fos.write( buffer, 0, bytesRead );
            bytesRead = inputStream.read( buffer, 0, buffLength );
        }

        inputStream.close();
        fos.flush();
        fos.close();

        this.saveScaledImage( target, 90, 60, "thumb_" );
        this.saveScaledImage( target, 360, 240, "mid_" );

        Image image = new Image();
        image.setName( target.getName() );
        image.setMimeType( ui.getFile().getContentType() );
        image.setLatitude( ui.getLatitude() );
        image.setLongitude( ui.getLongitude() );
        image.setCreated( now );

        imageDao.insert( image );
    }

    public File getUploadDir( Date date )
    {
        File dir = new File( config.getUploadPath(), new SimpleDateFormat( "yyyy/MM/dd" ).format( date ) );

        if( !dir.exists() )
            dir.mkdirs();

        return dir;
    }

    
    //TODO: We should use the database id for the filename, once available
    public File getValidTarget( String original, Date date )
    {
        int lastIndex = original.lastIndexOf(".");
        File uploadDir = getUploadDir( date );
        String ext;

        if( lastIndex > -1 )
            ext = original.substring( lastIndex );
        else
            ext = "";

        File target;

        do
        {
            target = new File( uploadDir, System.currentTimeMillis() + ext );
        }
        while( target.exists() );

        return target;
    }


    public void saveScaledImage( File src, int p_width, int p_height, String prefix )
    {
        try
        {
            BufferedImage image = ImageIO.read(src);

            int thumbWidth = p_width;
            int thumbHeight = p_height;

            // Make sure the aspect ratio is maintained, so the image is not skewed
            double thumbRatio = (double)thumbWidth / (double)thumbHeight;
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            double imageRatio = (double)imageWidth / (double)imageHeight;

            if (thumbRatio < imageRatio)
                thumbHeight = (int)(thumbWidth / imageRatio);

            else
              thumbWidth = (int)(thumbHeight * imageRatio);

            // Draw the scaled image
            BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = thumbImage.createGraphics();
            graphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
            graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

            // Write the scaled image to the outputstream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

            int quality = 100;
            param.setQuality((float)quality / 100.0f, false);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(thumbImage);

            File thumb = new File( src.getParent(), prefix + src.getName() );
            ImageIO.write(thumbImage, "jpg" , thumb );
        }
        catch (Exception e)
        {
            log.error( "Could not save thumbnail", e );
        }
    }



    public static void main(String[]args)
    {
        UploadServiceImpl service = new UploadServiceImpl();
        File dir = new File("C:\\temp\\flow\\upload\\2010\\08\\14");
        File[] list = dir.listFiles( new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.contains( "thumb_" ) && !name.contains( "mid_" );
            }
        } );

        for( File f : list )
            service.saveScaledImage( f, 360, 240, "mid_" );
            //service.saveScaledImage( f, 90, 60 );

    }

}



