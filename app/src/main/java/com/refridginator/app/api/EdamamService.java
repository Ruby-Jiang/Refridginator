package com.refridginator.app.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EdamamService {
    @GET("search?q=chicken&app_id=66e1ad0a&app_key=40b611f403c5665eb034a5bcafa94947&from=0&to=3&calories=591-722&health=alcohol-free")
    public Observable<RecipeResponseModel> getRecipe();
}