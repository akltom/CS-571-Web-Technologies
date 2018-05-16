package com.example.kinglunau.hw9;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;



public class SubPage03 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";

    private static final String TAG = "Tab3Fragment";

    ListView thisListView;
    StockNewsListAdapter newsadapter;
    ArrayList<StockNewsList> thenewsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_page03, container, false);

        thisListView = (ListView) view.findViewById(R.id.listview2);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // chchange the aapl to mainactuvity.type/
                    String newurl = "https://seekingalpha.com/api/sa/combined/" + MainActivity.stockSymbol + ".xml";
                    URL thisurl = new URL(newurl);
                    HttpURLConnection connect = (HttpURLConnection)thisurl.openConnection();
                    connect.setReadTimeout(10000);
                    connect.setConnectTimeout(15000);
                    connect.setRequestMethod("GET");
                    connect.setDoInput(true);
                    connect.connect();
                    InputStream stream = connect.getInputStream();
                    XMLPullParserHandler parser = new XMLPullParserHandler();
                    thenewsList = parser.parse(stream);
                        Log.d("sssssize",thenewsList.get(0).findA());
                        Log.d("sssssize",thenewsList.get(0).findT());
                        Log.d("sssssize",thenewsList.get(0).findLink());
                        Log.d("sssssize",thenewsList.get(0).findD());
                    stream.close();
                    newsadapter = new StockNewsListAdapter(getContext(), R.layout.newslayout, thenewsList);
                    newsadapter.notifyDataSetChanged();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
        thread.start();
        thisListView.setAdapter(newsadapter);
//        thisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("ere", "jrrjrjr");
//            }
//        });

        return view;
//
//                            }
//        );
//        requestQueue.add(jsonArrayRequest);



    }

}