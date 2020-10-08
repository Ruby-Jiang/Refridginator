package com.refridginator.app.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenFoodFactsService {

    @GET("product/{barcode}.json")
    public Observable<OpenFoodFactsResponseModel> getByBarcode(@Path("barcode") String barcode);
}
