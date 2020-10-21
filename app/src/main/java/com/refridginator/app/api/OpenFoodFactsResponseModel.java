package com.refridginator.app.api;

import androidx.annotation.Nullable;

public class OpenFoodFactsResponseModel {
    private int status;
    @Nullable
    private Product product;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Nullable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
