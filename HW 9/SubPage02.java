package com.example.kinglunau.hw9;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;



public class SubPage02 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";

    private static final String TAG = "Tab2Fragment";

    WebView myWebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View thisview = inflater.inflate(R.layout.fragment_sub_page02, container, false);

        // testing for google
        myWebView = (WebView) thisview.findViewById(R.id.webview);
        myWebView.addJavascriptInterface(new JavaScriptInterface(getActivity().getApplicationContext()), "Android");
        myWebView.loadUrl("file:///android_asset/highchart.html");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setAllowUniversalAccessFromFileURLs(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // testing for actual html
       // myWebView.loadUrl("javascript:refresh('"+getJsData()+"')");



        return thisview;
    }

}