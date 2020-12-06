package com.refridginator.app.data;

import androidx.lifecycle.LiveData;

import com.refridginator.app.api.OpenFoodFactsResponseModel;
import com.refridginator.app.api.OpenFoodFactsService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class FridgeItemRepository {
    private FridgeItemDao fridgeItemDao;
    private OpenFoodFactsService openFoodFactsService;

    @Inject
    public FridgeItemRepository(FridgeItemDao fridgeItemDao, OpenFoodFactsService openFoodFactsService) {
        this.fridgeItemDao = fridgeItemDao;
        this.openFoodFactsService = openFoodFactsService;
    }

    public LiveData<List<FridgeItem>> getAll() {
        return fridgeItemDao.getAll();
    }

    public Completable insert(FridgeItem fridgeItem) {
        return fridgeItemDao
                .insertAll(fridgeItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delete(FridgeItem fridgeItem) {
        return fridgeItemDao
                .delete(fridgeItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OpenFoodFactsResponseModel> searchByBarcode(String barcode) {
        return openFoodFactsService
                .getByBarcode(barcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
