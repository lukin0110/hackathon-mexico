package com.ktconexiones.flow.android.activity.tabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.app.Config;
import com.ktconexiones.flow.android.app.FlowApplication;
import com.ktconexiones.flow.android.app.Util;

import static com.ktconexiones.flow.android.app.Keys.Logging.FLOW;

public class FlowActivity extends Activity implements View.OnClickListener
{
    private ProgressDialog dialog;
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView( R.layout.flow );

        this.dialog = ProgressDialog.show( this, getString(R.string.common_loading) + "...", getString(R.string.common_retrieving_photos), true );
        final FlowActivity pointer = this;

        webView = (WebView) super.findViewById( R.id.flow_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(false);

        webView.setWebChromeClient( new WebChromeClient(){
           public void onProgressChanged(WebView view, int progress) {
             pointer.setProgress(progress * 1000);
           }
        });

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText( pointer, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pointer.dialog.dismiss();
            }

            
        });



        String url = getUrl();
        Button reload = (Button) super.findViewById( R.id.flow_reload );
        reload.setOnClickListener( this );

        if( url != null )
            this.webView.loadUrl( getUrl() );
        else
        {
            this.dialog.dismiss();
            Util.showGpsAlert( this );
        }
    }

    public void onClick(View view)
    {
        String url = getUrl();

        if( url != null )
        {
            this.dialog.show();
            this.webView.loadUrl( getUrl() );
        }
        else
            Util.showGpsAlert( this );
    }


    private String getUrl()
    {
        Location loc = FlowApplication.getInstance().getLocation();

        if( loc != null )
        {
            String url = Config.SERVER + "/rest/list/gps/" + loc.getLatitude() + "/" + loc.getLongitude() + ".html";
            Log.d( FLOW, "Url constructed: " + url );
            return url;
        }

        return null;
    }
    
}


