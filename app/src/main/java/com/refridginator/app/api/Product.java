package com.refridginator.app.api;

import com.google.gson.annotations.SerializedName;

public class Product {
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @SerializedName("product_name")
    private String productName;
}
