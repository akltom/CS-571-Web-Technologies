package com.example.kinglunau.hw9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kinglunau on 11/27/17.
 */

public class StockNewsListAdapter extends ArrayAdapter<StockNewsList> {
    private static final String TAG = "StockNewsListAdapter";
    private Context mContext;
    int mResources;

    public StockNewsListAdapter(Context context, int resource, ArrayList<StockNewsList> objects){
        super(context, resource, objects);
        mContext = context;
        mResources = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the person's information

        String thisauthor = getItem(position).findA();
        String date = getItem(position).findD();
        String htmllink = getItem(position).findLink();
        String thistit = getItem(position).findT();
        // create the person object with the information
        StockNewsList stockNews = new StockNewsList (thistit, thisauthor, date, htmllink);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResources, parent, false);


        TextView thisA = (TextView) convertView.findViewById(R.id.thisauthor);
        TextView thisD = (TextView) convertView.findViewById(R.id.thisdate);
        TextView thisT = (TextView) convertView.findViewById(R.id.thistitle);

        thisT.setText(thistit);
        thisD.setText(date);
        thisA.setText(thisauthor);


        return convertView;

    }
}