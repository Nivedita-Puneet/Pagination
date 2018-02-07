package org.nivi.mydailynews.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NEETU on 07-02-2018.
 * Class to hold news
 */

public class News {

    @SerializedName("title")
    private String title;
    @SerializedName("rows")
    private List<Category> categories;

    public News(String title, List<Category> categories) {
        this.title = title;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", categories=" + categories +
                '}';
    }
}
