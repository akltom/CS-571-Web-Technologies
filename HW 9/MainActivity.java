package com.example.kinglunau.hw9;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String[] fiveCompany = new String[] {"","","","",""};

    ArrayAdapter adapter;

    public static String stockSymbol;
    public static String stockSymbolLongVersion;

    public static String dataStcokSymbol;
    public static String lastPrice;
    public static String change;
    public static String changePercent;
    public static String timestamp;
    public static String open;
    public static String close;
    public static String daysRange;
    public static String volume;

    Button thisClear;
    Spinner spinnerFirst;
    Spinner spinnerSecond;
    public static ArrayList favlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        favlist = new ArrayList();
        super.onCreate(savedInstanceState);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        setContentView(R.layout.activity_main);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, fiveCompany);
        autoCompleteTextView.setThreshold(1); // Minimum number of character to display autocomplete
        autoCompleteTextView.setAdapter(adapter);
//        String url = "http://10.0.2.2:3000/autoComp/?input=aa";
//

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);


            stockSymbol =autoCompleteTextView.getText().toString();


        String url = "http://10.0.2.2:3000/autoComp/?input=" + stockSymbol;
        Log.d("STOCKSYMBOL:", stockSymbol);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                if (i == 5) {
                                    break;
                                }

                                JSONObject jsonObj = response.getJSONObject(i);
                                String oneString = jsonObj.getString("Symbol") + " - " + jsonObj.getString("Name") + " (" + jsonObj.getString("Exchange") + ")";
                                stockSymbolLongVersion = oneString;
                                fiveCompany[i] = oneString;
                               // Log.d("array:", fiveCompany[i]);


                            }

                            adapter.clear();

                            adapter.addAll(fiveCompany);
                           // Log.d("cccc", "dddaaaa");
                            adapter.notifyDataSetChanged();
                          //  Log.d("eee", "dddeeeeaaaa");

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "nothing found!", Toast.LENGTH_SHORT);
                        Log.d("eee", "asdfasdf");

                    }
                }

        );
        requestQueue.add(jsonArrayRequest);

        return true;
}

    public void clearButton() {

        thisClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText("");
            }
        });
    }

    public void sort() {
        String property = spinnerFirst.getSelectedItem().toString();
        String range = spinnerSecond.getSelectedItem().toString();
        int favlistsize=100;

        int counter = 0;
        if (property.equals ("Symbol") && range.equals("Ascending")){
            for (int i=0; i<favlistsize; ++i){
                favlist.set(i, MainActivity.stockSymbol);
                favlist.remove(1+i);
                break;
            }
        }
        if (property.equals ("Default") ){
            for (int i=0; i<favlistsize; ++i){
                favlist.set(i, MainActivity.stockSymbol);
                favlist.remove(1+i);
                break;
            }
        }
        if (range.equals ("Descending") ){
            for (int i=0; i<favlistsize; ++i){
                favlist.set(i, MainActivity.stockSymbol);
                favlist.remove(1+i);
                break;
            }
        }
        if (property.equals ("Change") && range.equals("Ascending")){
            for (int i=0; i<favlistsize; ++i){
                favlist.set(i, MainActivity.stockSymbol);
                favlist.remove(1+i);
                break;
            }
        }
        if (property.equals ("Price") && range.equals("Ascending")){
                for (int i=0; i<favlistsize; ++i){
                favlist.set(i, MainActivity.stockSymbol);
                favlist.remove(1+i);
                break;
            }
        }
    }




    public void onClick1(View v){
        if (v.getId()==R.id.getquote){ // id of the button
            Intent i = new Intent(MainActivity.this, stockDetails.class);  //  stockDetails
            startActivity(i);
        }
    }
}
