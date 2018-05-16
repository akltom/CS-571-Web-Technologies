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
 * Created by kinglunau on 11/25/17.
 */

public class RightListAdapter extends ArrayAdapter<rightString> {
    private static final String TAG = "RightListAdapter";
    private Context mContext;
    int mResources;

    public RightListAdapter(Context context, int resource, ArrayList<rightString> objects){
        super(context, resource, objects);
        mContext = context;
        mResources = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the person's information
        String name = getItem(position).getName();


        // create the person object with the information



        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResources, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.right);


        tvName.setText(name);


        return convertView;


    }
}
