package com.example.kinglunau.hw9;

/**
 * Created by kinglunau on 11/27/17.
 */

public class StockNewsList {


    private String date;
    private String htmllink;
    private String title;
    private String author;

    public StockNewsList(String title, String author, String date, String htmllink) {
        this.title = title;
        this.author=author;
        this.date = date;
        this.htmllink = htmllink;
    }

    public String findT(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String findA(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String findD(){
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }

    public String findLink(){
        return htmllink;
    }

    public void setLink(String link){
        this.htmllink=htmllink;
    }

}
