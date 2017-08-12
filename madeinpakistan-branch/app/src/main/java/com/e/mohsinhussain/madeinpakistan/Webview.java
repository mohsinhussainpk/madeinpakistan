package com.e.mohsinhussain.madeinpakistan;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Webview extends AppCompatActivity {

    private  static WebView browser;
    ProgressBar progressBar,progressBar1;
    int progress=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        browser = (WebView)findViewById(R.id.webView);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar1= (ProgressBar) findViewById(R.id.progressBar1);

        CheckConnetivity check = new CheckConnetivity();
        Boolean conn = check.isNetworkAvailable(this.getApplicationContext());
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.FAR;
        WebChromeClient webChromeClient = new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

            }
        };

        if(conn)
        {


            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
            progressBar.getIndeterminateDrawable().setColorFilter(getResources()
                    .getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);


            //progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            browser.getSettings().setJavaScriptEnabled(true);

            browser.getSettings().setDefaultTextEncodingName("utf-8");
            browser.getSettings().setLoadWithOverviewMode(true);
            browser.getSettings().setDefaultZoom(zoomDensity);
            browser.getSettings().setSupportZoom(true);
            browser.getSettings().setBuiltInZoomControls(true);
            browser.requestFocus(View.FOCUS_DOWN);

            browser.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            browser.setWebChromeClient(webChromeClient);
            browser.setWebViewClient(this.getWebViewClient());

            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
            String url=getIntent().getStringExtra("brandUrl");
            browser.loadUrl(url);


//            WebSettings webSettings = browser.getSettings();
//            webSettings.setJavaScriptEnabled(true);
//            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//            String url=getIntent().getStringExtra("brandUrl");
//
//            //Toast.makeText(this,url,Toast.LENGTH_LONG).show();
//            browser.getSettings().getJavaScriptEnabled();
//            browser.getSettings().setBuiltInZoomControls(true);
//            browser.loadUrl(url);
////            browser.setWebViewClient(new webclient());

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                // chromium, enable hardware acceleration
//                browser.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//            } else {
//                // older android version, disable hardware acceleration
//                browser.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//            }
//            browser.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);


        }
        else
        {
            check.connectivityMessage("Check Network Connection",this);
        }





        // TextView textView= (TextView) findViewById(R.id.textview);


    }
    public WebViewClient getWebViewClient() {

        return new WebViewClient() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressBar.setVisibility(View.GONE);
                progressBar1.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                progressBar.setVisibility(View.VISIBLE);

                //Some checks
                if (url.contains("load.elsewhere.com")) {
                    //If you want to handle where load.elsewhere.com is loaded, say in another external browser
                    progressBar.setIndeterminate(true);

                    //startActivity to load elsewhere

                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        };
    }

    public class webclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
            }

    @Override
    public void onBackPressed() {


        super.onBackPressed();

        if (MainActivity.mInterstitialAd.isLoaded()) {
            MainActivity.mInterstitialAd.show();
        }
        //Toast.makeText(this,"onBackPressed",Toast.LENGTH_SHORT).show();

        finish();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);

    }


}







