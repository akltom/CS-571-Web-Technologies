package com.example.kinglunau.hw9;

import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;








public class SubPage01 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";

    private static final String TAG = "Tab1Fragment";
//    private String stockSymbol;
//    private String lastPrice;
//    private String change;
//    private String timestamp;
//    private String open;
//    private String close;
//    private String dayRange;
ImageButton FacebookButton;

//    private String volume;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






// with no click on autocompelte:
// fb , aa works, msft doesnt work

                String thisStockSymbol = MainActivity.stockSymbolLongVersion; // get the stock symbol with long aapl-fdffd -nyse

        String tempStr = thisStockSymbol;

        if (tempStr.contains(("-"))) {
//            thisStockSymbol = tempStr.substring(0, tempStr.indexOf("-")-1);
            thisStockSymbol = tempStr.substring(0, tempStr.indexOf(" "));
        }

        String tag_json_obj = "json_obj_req";
        String json_url = "http://10.0.2.2:3000/getStockDetails?stockName=" + thisStockSymbol;

        Log.d("2222", tempStr);
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                json_url, null,
                new Response.Listener<JSONObject>() {
                    //在这个方法里，成功获取到了数据
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());

                        try {
                            MainActivity.dataStcokSymbol = response.getString("stockSymbol");
                         //   Log.d("TAG", MainActivity.dataStcokSymbol);
                            MainActivity.lastPrice = response.getString("lastPrice");
                         //   Log.d("TAG", MainActivity.lastPrice);
                            MainActivity.change = response.getString("changePrice");
                         //   Log.d("TAG", MainActivity.change);
                            MainActivity.changePercent = response.getString("changePricePercent");
                          //  Log.d("TAG", MainActivity.changePercent);
                            MainActivity.timestamp = response.getString("timestamp");
                         //   Log.d("TAG", MainActivity.timestamp);
                            MainActivity.open = response.getString("open");
                         //   Log.d("TAG", MainActivity.open);
                            MainActivity.close = response.getString("close");
                          //  Log.d("TAG", MainActivity.close);
                            MainActivity.daysRange = response.getString("dayRange");
                         //   Log.d("TAG", MainActivity.daysRange);
                            MainActivity.volume = response.getString("volume");
                         //   Log.d("TAG", MainActivity.volume);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();

                        }

//                        AlertDialog alertDialog = new AlertDialog.Builder(stockDetails.this).create();
//                        alertDialog.setTitle("Alert");
//                        alertDialog.setMessage(response.toString());
//
//
//                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                });
//                        alertDialog.show();
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            //在这个方法里，打印错误信息
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });
        requestQueue.add(jsonObjReq);



        View view = inflater.inflate(R.layout.fragment_sub_page01, container, false);


        FacebookButton = view.findViewById(R.id.Facebook);
        FacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fburl = "https://developers.facebook.com";
                Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();

            }
        });

        ListView mListView = (ListView) view.findViewById(R.id.listvw);
        rightString this1 = new rightString (MainActivity.dataStcokSymbol);
        rightString this2 = new rightString (MainActivity.lastPrice);
        rightString this3 = new rightString (MainActivity.change);
        rightString this4 = new rightString (MainActivity.changePercent);
        rightString this5 = new rightString (MainActivity.timestamp);
        rightString this6 = new rightString (MainActivity.close);
        rightString this7 = new rightString (MainActivity.daysRange);
        rightString this8 = new rightString (MainActivity.volume);





        ArrayList<rightString> rightList = new ArrayList<>();
        rightList.add(this1);
        rightList.add(this2);
        rightList.add(this3);
        rightList.add(this4);
        rightList.add(this5);
        rightList.add(this6);
        rightList.add(this7);
        rightList.add(this8);


        RightListAdapter adapter2 = new RightListAdapter(getContext(), R.layout.rightadapter_view_layout, rightList);
        mListView.setAdapter(adapter2);

        ListView ListView1 = (ListView) view.findViewById(R.id.listVW1);
        leftString first = new leftString("Stock Symbol");
        leftString second = new leftString("Last Price");
        leftString third = new leftString("Change");
        leftString fourth = new leftString("Timestamp");
        leftString fifth = new leftString("Open");
        leftString sixth = new leftString("Close");
        leftString seventh = new leftString("Day's Range");
        leftString eightth = new leftString("Volume");
        ArrayList<leftString> leftList = new ArrayList<>();

        leftList.add(first);
        leftList.add(second);
        leftList.add(third);
        leftList.add(fourth);
        leftList.add(fifth);
        leftList.add(sixth);
        leftList.add(seventh);
        leftList.add(eightth);
        LeftListAdapter adapter1 = new LeftListAdapter(getContext(), R.layout.leftadapter_view_layout, leftList);
        ListView1.setAdapter(adapter1);


        return view;
    }














}
