package com.refridginator.app.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;


@Dao
public interface FridgeItemDao {
    @Query("SELECT * FROM fridge_items")
    LiveData<List<FridgeItem>> getAll();

    @Insert
    Completable insertAll(FridgeItem... fridgeItems);

    @Delete
    Completable delete(FridgeItem fridgeItem);
}
