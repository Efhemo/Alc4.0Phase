package com.efhems.alc4phase1;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AboutALCActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    public static final String link = "https://andela.com/alc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        progressBar = findViewById(R.id.webProgress);
        WebView webView = findViewById(R.id.webView);
        progressBar.setVisibility(View.GONE);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * Notify the host application that the WebView will load the resource
             * specified by the given url.
             *
             * @param view The WebView that is initiating the callback.
             * @param url  The url of the resource the WebView will load.
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                progressBar.incrementProgressBy(10);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);

    }
}
