package com.ktconexiones.flow.server.view;

import com.ktconexiones.flow.server.business.services.UploadService;
import com.ktconexiones.flow.server.domain.UploadItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/rest/upload")
public class UploadController
{
    @Autowired
    private UploadService service;
    private Logger log = Logger.getLogger( getClass() );

    @RequestMapping(value="/form.html", method= RequestMethod.GET)
    public String form( Model model )
    {
        log.debug( "showing form" ); 
        model.addAttribute(new UploadItem());
        return "form";
    }

    @RequestMapping(value="/form.html", method=RequestMethod.POST)
    public String process(
            UploadItem upload,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response ) throws IOException
    {
        if( result.hasErrors() || upload.getFile().getSize() == 0 )
        {
            request.setAttribute( "required", "All fields are required!" );
            return "form";
        }

        log.debug( "processing form " + upload + ", " + result.hasErrors() + ", " );
        log.debug( "JAJA: " + upload.getFile().getOriginalFilename() + ", " + upload.getLatitude() + ", " + upload.getLongitude() );

        service.save( upload );

        // avoid that my hungry friend tomcat eats to much memory
        // Introspector.flushCaches();
        response.getWriter().println( "Saved" );
        return null;
    }

 
}

