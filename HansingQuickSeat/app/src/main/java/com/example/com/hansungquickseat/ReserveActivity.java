package com.example.com.hansungquickseat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by com on 2019-06-25.
 */

public class ReserveActivity extends AppCompatActivity {
    private WebView mWebView;
    private  String myURL="http://hsel.hansung.ac.kr/";
    String postData ="home_login_mloc_code="+"HSEL"+"&home_login_id=1494017"+"&home_login_password=!qweasd9577"+"&home_login_id_save_yn=N";
    String str;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewlayout);
        // 웹뷰 셋팅팅
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        //mWebView.loadUrl("http://www.pois.co.kr/mobile/login.do");

        try {
            str="home_login_mloc_code="+ URLEncoder.encode("HSEL","UTF-8")+"&home_login_id="+ URLEncoder.encode("아이디","UTF-8")+"&home_login_password="+ URLEncoder.encode("비밀번호","UTF-8")+"&home_login_id_save_yn="+ URLEncoder.encode("N","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mWebView.loadUrl(myURL);
        mWebView.postUrl("https:/https://hsel.hansung.ac.kr/home_security_login_write_prss.mir",str.getBytes());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());
        mWebView.loadUrl(myURL); // 접속 URL

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }


}

