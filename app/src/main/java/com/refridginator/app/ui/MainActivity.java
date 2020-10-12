package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.refridginator.app.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void launchFridgeStats (View view){
        Intent intent = new Intent(this, FridgeStats.class);
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
    public void launchOpen (View view){
        Intent intent = new Intent(this, OpenFridge.class);
        startActivity(intent);
    }
}