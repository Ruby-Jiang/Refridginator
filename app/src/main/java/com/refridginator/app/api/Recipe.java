package com.refridginator.app.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("url")
    @Expose
    private String url;

    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public String getUrl() {
        return url;
    }
}
//    public String getRecipeName() { return recipeName; }
//
//    public void setRecipeName(String productName) {
//        this.recipeName = recipeName;
//    }
//
//    @SerializedName("recipe_name")
//    private String recipeName;
//}