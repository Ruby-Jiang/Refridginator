package com.refridginator.app.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeResponseModel {
    @SerializedName("results")
    @Expose
    private ArrayList<Recipe> results;

    public ArrayList<Recipe> getResults() {
        return results;
    }
    @NonNull
    @Override
    public String toString() {
        return results.toString();
    }
}

//    private String title;
//    private String image;
//    private String url;
//
//    public RecipeResponseModel(String title, String image, String url) {
//        this.title = title;
//        this.image = image;
//        this.url = url;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//}
