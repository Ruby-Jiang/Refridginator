package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.util.Log;


import com.refridginator.app.R;
import com.refridginator.app.data.FridgeItem;
import com.refridginator.app.viewmodels.StorageViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.disposables.CompositeDisposable;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private StorageViewModel storageViewModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageViewModel = new ViewModelProvider(this).get(StorageViewModel.class);

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