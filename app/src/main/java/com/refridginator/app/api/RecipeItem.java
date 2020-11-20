package com.refridginator.app.api;

public class RecipeItem {
    private String mImageUrl;
    private String mTitle;
    private String mURL;

    public RecipeItem (String imageUrl, String title, String url){
        mImageUrl = imageUrl;
        mTitle = title;
        mURL = url;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public String getTitle() {

        return mTitle;
    }

    public String getURL() {

        return mURL;
    }
}
