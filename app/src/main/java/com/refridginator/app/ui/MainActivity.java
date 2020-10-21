package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.refridginator.app.R;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL="https://api.edamam.com/search?q=chicken&app_id=66e1ad0a&app_key=40b611f403c5665eb034a5bcafa94947&from=0&to=3&calories=591-722&health=alcohol-free";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Rest Response", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest Response", error.toString());

            }
        }
        );
        requestQueue.add(objectRequest);

        ImageView image = findViewById(R.id.addFood);
//        image.setOnClickListener(v -> {
//            Intent intent = new Intent(this, AddFoodActivity.class);
//
//            startActivity(intent);
//
//        });
    }
    public void launchAddFood (View view){
        Intent intent = new Intent(this, AddFoodActivity.class);
        startActivity(intent);
    }
    public void launchAddGrocery (View view){
        Intent intent = new Intent(this, AddGrocery.class);
        startActivity(intent);
    }
    public void launchRecipeRecs (View view){
        Intent intent = new Intent(this, RecipeRecs.class);
        startActivity(intent);
    }
    public void launchStorage (View view){
        Intent intent = new Intent(this, Storage.class);
        startActivity(intent);
    }
}