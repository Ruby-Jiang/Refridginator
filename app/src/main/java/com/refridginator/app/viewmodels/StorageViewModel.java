package com.refridginator.app.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.refridginator.app.api.OpenFoodFactsResponseModel;
import com.refridginator.app.data.FridgeItem;
import com.refridginator.app.data.FridgeItemRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class StorageViewModel extends ViewModel {
    private FridgeItemRepository fridgeItemRepository;
    private static final String TAG = "StorageViewModel";

    @Inject
    public StorageViewModel(FridgeItemRepository fridgeItemRepository) {
        this.fridgeItemRepository = fridgeItemRepository;
    }

    public LiveData<List<FridgeItem>> getAll() {
        return fridgeItemRepository.getAll();
    }

    @SuppressLint("CheckResult")
    public void insert(FridgeItem fridgeItem) {
        fridgeItemRepository.insert(fridgeItem).subscribe(() -> {
            Log.d(TAG, "insert: success");
        }, error -> {
            Log.e(TAG, "insert: failure", error);
        });
    }

    public Observable<OpenFoodFactsResponseModel> searchByBarcode(String barcode) {
        return fridgeItemRepository.searchByBarcode(barcode);
    }
}
