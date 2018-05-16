package com.example.kinglunau.hw9;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class stockDetails extends AppCompatActivity {



    private static final String TAG = "stockDetails";
    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);
    //    Log.d(TAG, "STARTING");

//        String thisStockSymbol = MainActivity.stockSymbol; // get the stock symbol
//        String tag_json_obj = "json_obj_req";
//        String json_url = "http://10.0.2.2:3000/getStockDetails?stockName=" + thisStockSymbol;
//        final RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                json_url, null,
//                new Response.Listener<JSONObject>() {
//                    //在这个方法里，成功获取到了数据
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("TAG", response.toString());
//
//                        try {
//                            MainActivity.dataStcokSymbol = response.getString("stockSymbol");
//                         //   Log.d("TAG", MainActivity.dataStcokSymbol);
//                            MainActivity.lastPrice = response.getString("lastPrice");
//                         //   Log.d("TAG", MainActivity.lastPrice);
//                            MainActivity.change = response.getString("changePrice");
//                         //   Log.d("TAG", MainActivity.change);
//                            MainActivity.changePercent = response.getString("changePricePercent");
//                          //  Log.d("TAG", MainActivity.changePercent);
//                            MainActivity.timestamp = response.getString("timestamp");
//                         //   Log.d("TAG", MainActivity.timestamp);
//                            MainActivity.open = response.getString("open");
//                         //   Log.d("TAG", MainActivity.open);
//                            MainActivity.close = response.getString("close");
//                          //  Log.d("TAG", MainActivity.close);
//                            MainActivity.daysRange = response.getString("dayRange");
//                         //   Log.d("TAG", MainActivity.daysRange);
//                            MainActivity.volume = response.getString("volume");
//                         //   Log.d("TAG", MainActivity.volume);
//
//
//
//
//
//                        }
//                        catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//
//
//
//
//
//
//
//
////                        AlertDialog alertDialog = new AlertDialog.Builder(stockDetails.this).create();
////                        alertDialog.setTitle("Alert");
////                        alertDialog.setMessage(response.toString());
////
////
////                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
////                                new DialogInterface.OnClickListener() {
////                                    public void onClick(DialogInterface dialog, int which) {
////                                        dialog.dismiss();
////                                    }
////                                });
////                        alertDialog.show();
//                        //pDialog.hide();
//                    }
//                }, new Response.ErrorListener() {
//            //在这个方法里，打印错误信息
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                // hide the progress dialog
//                //pDialog.hide();
//            }
//        });
//        requestQueue.add(jsonObjReq);



        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);






//        String[] items = {"Stock Symbol", "Last Price", "Change", "Timestamp", "Open", "Close", "Day's Range", "Volume"};
//        ListAdapter stockAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); // This is a converter to convert a items array to listitem
//        ListView stocklist = (ListView) findViewById(R.id.stocklist);
//        stocklist.setAdapter(stockAdapter);

    }
    public void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SubPage01(), "CURRENT");
        adapter.addFragment(new SubPage02(), "HISTORICAL");
        adapter.addFragment(new SubPage03(), "NEWS");
        viewPager.setAdapter(adapter);
    }



//        }
//
//
//    }
}
