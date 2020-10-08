package com.refridginator.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.refridginator.app.api.OpenFoodFactsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String OPEN_FOOD_FACTS_BASE_URL = "https://world.openfoodfacts.org/api/v0/";
    private Gson gson;

    public NetworkModule() {
        this.gson = new GsonBuilder()
                .setLenient()
                .create();
    }

    @Singleton
    @Provides
    OpenFoodFactsService provideOpenFoodFactsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPEN_FOOD_FACTS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(OpenFoodFactsService.class);
    }
}
