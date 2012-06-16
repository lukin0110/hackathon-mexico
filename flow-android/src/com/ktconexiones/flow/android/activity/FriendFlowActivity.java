package com.ktconexiones.flow.android.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ktconexiones.flow.android.R;
import com.ktconexiones.flow.android.app.Config;
import com.ktconexiones.flow.android.app.Keys;
import com.ktconexiones.flow.android.app.Util;

import static com.ktconexiones.flow.android.app.Keys.Logging.FLOW;

// No time to waste ... so more/less silly copy/paste from FlowActivity :(
public class FriendFlowActivity extends Activity implements View.OnClickListener
{
    private ProgressDialog dialog;
    private WebView webView;
    private boolean justCreated = true;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView( R.layout.flow_friend );
        this.justCreated = true;

        this.dialog = ProgressDialog.show( this, getString(R.string.common_loading) + "...", getString(R.string.common_retrieving_photos), true );
        final FriendFlowActivity pointer = this;

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

        this.webView.loadUrl( getUrl() );

        Button reload = (Button) super.findViewById( R.id.flow_reload );
        reload.setOnClickListener( this );
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        TextView tv = (TextView) super.findViewById( R.id.flow_name );
        tv.setText( super.getIntent().getExtras().getString( Keys.Flow.NAME ) );
        tv.refreshDrawableState();

        if( !justCreated )
        {
            this.dialog.show();
            this.webView.loadUrl( getUrl() );
        }
        else
        {
            justCreated = false;
        }
    }

    public void onClick(View view)
    {
        this.dialog.show();
        this.webView.loadUrl( getUrl() );
    }

    private String getUrl()
    {
        String phone = super.getIntent().getExtras().getString( Keys.Flow.PHONE );
        String url = Config.SERVER + "/rest/list/phone/" + phone + ".html";
        Log.d( FLOW, "Url constructed: " + url );
        return url;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return Util.bindMenu( this, menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return Util.menuItemSelected( this, item );
    }
    

}


