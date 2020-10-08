package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.util.Log;


import com.refridginator.app.R;
import com.refridginator.app.di.DaggerAppComponent;
import com.refridginator.app.di.RoomModule;
import com.refridginator.app.data.FridgeItem;
import com.refridginator.app.viewmodels.StorageViewModel;
import com.refridginator.app.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private StorageViewModel storageViewModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAppComponent.builder()
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);

        storageViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(StorageViewModel.class);

        FridgeItem fridgeItem = new FridgeItem();
        fridgeItem.setId((long) 6);
//        storageViewModel.insert(fridgeItem);
        storageViewModel.getAll().observe(this, f-> {
            Log.i("getSize(): ", f.size()+"");
        });


        storageViewModel.searchByBarcode("5011038133450").subscribe(
                r -> {
                    Log.i(TAG, "onCreate: " + r.getProduct().getProductName());
                },
                e -> {
                    Log.e(TAG, "onCreate: ", e);
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}