package com.example.slope.androiddriver.basicclass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Slope on 2016/9/17.
 */
public class News  {
    private String title;
    private String source;
    private String article_url;

    public News(String title, String source, String article_url) {
        this.title = title;
        this.source = source;
        this.article_url = article_url;
    }

    public News(String title, String article_url) {
        this.title = title;
        this.article_url = article_url;
    }

    protected News(Parcel in) {
        title = in.readString();
        source = in.readString();
        article_url = in.readString();
    }



    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "News{" +
                "article_url='" + article_url + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                '}';
    }


}
