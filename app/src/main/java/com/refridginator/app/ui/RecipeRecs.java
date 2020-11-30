package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.refridginator.app.R;
import com.refridginator.app.api.RecipeAdapter;
import com.refridginator.app.api.RecipeItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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

        EditText editText = findViewById(R.id.edit_recipes);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                parseJSON(v.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void parseJSON(String keyword) {
        String url = String.format("https://api.edamam.com/search?q=%s&app_id=66e1ad0a&app_key=40b611f403c5665eb034a5bcafa94947", keyword);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            mRecipeList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject innerObject = jsonArray.getJSONObject(i);
                                JSONObject recipe = innerObject.getJSONObject("recipe");
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
