package com.example.kinglunau.hw9;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by kinglunau on 11/27/17.
 */

public class XMLPullParserHandler {
    String astring;
    StockNewsList thenewsData;
    ArrayList <StockNewsList> thenewsArrayList = new ArrayList<StockNewsList>();



    public ArrayList<StockNewsList> parse(InputStream stream){

        try{
            thenewsData = new StockNewsList("title:", "author:", "date:", "link:");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(stream, null);

            int eventType = parser.getEventType();
            String checkArt = "";

            while (eventType!=XmlPullParser.END_DOCUMENT){

                String abc = parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if(abc.equalsIgnoreCase("item")) {
                            thenewsData = new StockNewsList("title", "author","date","link");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        astring = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(abc.equalsIgnoreCase("item")) {
                            if (checkArt.contains("article")){
                                thenewsArrayList.add(thenewsData);
//                                Log.d("sssssize",newsCol.getAuthor());
//                                Log.d("sssssize",newsCol.getTitle());
//                                Log.d("sssssize",newsCol.getLink());
//                                Log.d("sssssize",newsCol.getDate());
                            }
                        }

                        else if (abc.equalsIgnoreCase("sa:author_name")){
                            thenewsData.setAuthor("Author: "+ astring);
                        }
                        else if(abc.equalsIgnoreCase("title")) {
                            thenewsData.setTitle(astring);
                        }

                        else if (abc.equalsIgnoreCase("link")){
                            thenewsData.setLink(astring);
                            checkArt = astring;
                        }
                        else if(abc.equalsIgnoreCase("pubDate")){
                            thenewsData.setDate("Date: "+ astring.substring(0, 26)+"PDT");
                        }
                        break;
                    default:
                        break;

                }
                eventType = parser.next();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        catch(XmlPullParserException e){
            e.printStackTrace();

        }
        return thenewsArrayList;
    }
}
