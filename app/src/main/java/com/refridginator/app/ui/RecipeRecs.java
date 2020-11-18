package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.refridginator.app.R;
import com.refridginator.app.api.EdamamService;
import com.refridginator.app.api.Recipe;
import com.refridginator.app.api.RecipeRecyclerViewAdapter;
import com.refridginator.app.api.RecipeResponseModel;
import androidx.core.app.CoreComponentFactory;

import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRecs extends AppCompatActivity {
    private static final String BASE_URL = "https://api.edamam.com/";
    private static final String EDAMAM_API_KEY = "40b611f403c5665eb034a5bcafa94947";
    private RecyclerView rRecyclerView;
    private RecipeRecyclerViewAdapter rMyRecipeAdapter;
    private ArrayList<Recipe> rRecipeList;
    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_recs);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        EdamamService edamamService = retrofit.create(EdamamService.class);
//        AppDatabase recipeDatabaseAPI = retrofit.create(AppDatabase.class);
        edamamService.getRecipe().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(results -> {
                    Log.d("results: success", results.toString());
                }, error -> {
                    Log.e("results: fail", error.toString());
                }
        );


        rRecyclerView = findViewById(R.id.recipe_recycler_view);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


//        edamamService.getRecipe(new Callback<RecipeResponseModel>() {


//        @Override
//            public void onResponse(Call<RecipeResponseModel> call, Response<RecipeResponseModel> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT.show());
//                    return;
//                }
//                RecipeResponseModel recipe_response = response.body();
//                assert recipe_response != null;
//                rRecipeList = recipe_response.getResults();
//
//                rMyRecipeAdapter = new RecipeRecyclerViewAdapter(RecipeRecs.this, rRecipeList);
//                rRecyclerView.setAdapter(rMyRecipeAdapter);
//
//            }

//            @Override
//            public void onFailure(Call<RecipeResponseModel> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    }
}