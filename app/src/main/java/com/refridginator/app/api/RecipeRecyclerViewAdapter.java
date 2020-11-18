package com.refridginator.app.api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refridginator.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyRecipeViewHolder> {
    private Context rContext;
    private ArrayList<Recipe> rRecipeList;
    public RecipeRecyclerViewAdapter(Context context, ArrayList<Recipe> recipeArrayList){
        rContext = context;
        rRecipeList = recipeArrayList;
    }
    private static String IMAGE_BASE_URL = "";

    @NonNull
    @Override
    public MyRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(rContext).inflate(R.layout.activity_recipe_recs, parent,false);
        return new MyRecipeViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyRecipeViewHolder holder, int position){
        Recipe currentRecipe = rRecipeList.get(position);
        String recipeTitle = "";
        holder.rRecipeTitle.setText(recipeTitle);
        String recipeImagePath = "";
        Log.v("imagepath", recipeImagePath);

        Picasso.get().load(recipeImagePath).fit().centerInside().into(holder.rRecipeImage);
    }
    @Override
    public int getItemCount(){
        return rRecipeList.size();
    }
    public class MyRecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView rRecipeImage;
        public TextView rRecipeTitle;
        public MyRecipeViewHolder(@NonNull View itemView){
            super(itemView);
            rRecipeImage = itemView.findViewById(R.id.recipe_image);
            rRecipeTitle = itemView.findViewById(R.id.recipe_title);
        }
    }
}
