package com.foryatto.onetoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.xuexiang.xui.widget.actionbar.TitleBar;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        titleBarInit();

        Intent intent = getIntent();
        openUrl(intent.getStringExtra("url"), intent.getIntExtra("mode", 1));
    }

    private void titleBarInit(){
        TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar_web);
        titleBar.setLeftClickListener(v->{
            Intent intent = new Intent(WebActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void openUrl(String url, int mode){
        if(mode == 0){
            // "file:///android_asset/weather.html"
            WebView webview = (WebView) findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);
        }
        else{
            WebView webview = (WebView) findViewById(R.id.webview);
            webview.loadUrl(url);
        }
    }
}