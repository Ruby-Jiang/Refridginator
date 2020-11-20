package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.refridginator.app.R;
//import com.refridginator.app.api.EdamamService;
//import com.refridginator.app.api.Recipe;
import com.refridginator.app.api.RecipeAdapter;
import com.refridginator.app.api.RecipeItem;
import com.refridginator.app.api.RecipeAdapter;
//import com.refridginator.app.api.RecipeRecyclerViewAdapter;
//import com.refridginator.app.api.RecipeResponseModel;
import androidx.core.app.CoreComponentFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRecs extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private ArrayList<RecipeItem> mRecipeList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recipe_recs);

            mRecyclerView = findViewById(R.id.recipe_recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setHasFixedSize(true);

            mRecipeList = new ArrayList<>();
            mRequestQueue = Volley.newRequestQueue(this);
            parseJSON();

        }

    private void parseJSON() {
        String url = "https://api.edamam.com/search?q=chicken&app_id=66e1ad0a&app_key=40b611f403c5665eb034a5bcafa94947";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for(int i = 0; i < jsonArray.length()-1; i++){
                                JSONObject innerObject = jsonArray.getJSONObject(i);
                                JSONObject recipe = innerObject.getJSONObject("recipe");
//                                RecipeItem recipeItem = new RecipeItem();

                                JSONObject hit = jsonArray.getJSONObject(i);

                                String recipeName = recipe.getString("label");
                                String imageUrl = recipe.getString("image");
                                String recipeLink = recipe.getString("url");

                                mRecipeList.add(new RecipeItem(recipeName, imageUrl, recipeLink));
                            }
                            mRecipeAdapter = new RecipeAdapter(RecipeRecs.this, mRecipeList);
                            mRecyclerView.setAdapter(mRecipeAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
        System.out.println(request);
        System.out.println(mRecipeList);

    }

}
//    private static final String BASE_URL = "https://api.edamam.com/";
//    private static final String EDAMAM_API_KEY = "40b611f403c5665eb034a5bcafa94947";
//    private RecyclerView rRecyclerView;
//    private RecipeRecyclerViewAdapter rMyRecipeAdapter;
//    private ArrayList<Recipe> rRecipeList;
//    private Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipe_recs);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        EdamamService edamamService = retrofit.create(EdamamService.class);
////        AppDatabase recipeDatabaseAPI = retrofit.create(AppDatabase.class);
//        edamamService.getRecipe().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(results -> {
//                    Log.d("results: success", results.toString());
//                }, error -> {
//                    Log.e("results: fail", error.toString());
//                }
//        );
//
//
//        rRecyclerView = findViewById(R.id.recipe_recycler_view);
//        rRecyclerView.setHasFixedSize(true);
//        rRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


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
//    }
//}