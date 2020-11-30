package com.refridginator.app.api;

import android.content.Context;
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

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context mContext;
    private ArrayList<RecipeItem> mRecipeList;

    public RecipeAdapter(Context context, ArrayList<RecipeItem> recipeList){
        mContext = context;
        mRecipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recipe_item, parent,false);
    return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeItem currentItem = mRecipeList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String recipeTitle = currentItem.getTitle();
        String recipeURL = currentItem.getURL();

        holder.mTextViewTitle.setText(imageUrl);
        holder.mTextViewURL.setText("Link to Recipe: " + recipeURL);
        Picasso.get().load(recipeTitle).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {

        return mRecipeList.size();
    }
    public void filterList(ArrayList<RecipeItem> filteredList){
        mRecipeList = filteredList;
        notifyDataSetChanged();

    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewURL;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_picture);
            mTextViewTitle = itemView.findViewById(R.id.text_view_recipe_title);
            mTextViewURL = itemView.findViewById(R.id.text_view_url);
        }
    }
}
