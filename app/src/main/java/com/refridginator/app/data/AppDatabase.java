package com.refridginator.app.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//import com.refridginator.app.api.RecipeResponseModel;

import retrofit2.Call;


@Database(entities = {FridgeItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FridgeItemDao fridgeItemDao();
}
