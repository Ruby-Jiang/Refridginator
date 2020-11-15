package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.util.TableInfo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.refridginator.app.R;
import com.refridginator.app.data.FridgeItem;
import com.refridginator.app.viewmodels.StorageViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Storage extends AppCompatActivity {
    private StorageViewModel viewModel;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        viewModel = new ViewModelProvider(this).get(StorageViewModel.class);
        tableLayout = findViewById(R.id.storageTable);

//        // Test data
//        FridgeItem fridgeItem = new FridgeItem();
//        fridgeItem.setItemName("bread");
//        fridgeItem.setExpirationDate("2020-1-1");
//        viewModel.insert(fridgeItem);
//
//        fridgeItem = new FridgeItem();
//        fridgeItem.setItemName("milk");
//        fridgeItem.setExpirationDate("2020-1-2");
//        viewModel.insert(fridgeItem);
//
//        fridgeItem = new FridgeItem();
//        fridgeItem.setItemName("eggs");
//        fridgeItem.setExpirationDate("2020-1-2");
//        viewModel.insert(fridgeItem);

        viewModel.getAll().observe(this, this::onGetAll);
    }

    /**
     * Callback method for getAll() fridge items
     * @param fridgeItems
     */
    private void onGetAll(List<FridgeItem> fridgeItems) {
        tableLayout.removeAllViews(); // clear all rows

        for (FridgeItem fridgeItem : fridgeItems) { // add rows to table
            TableRow row = new TableRow(this);
            //this code sets the parameters for each table row as they are generated
            //it provides extra spacing between the rows for better readability
            TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
            int leftMargin=20;
            int topMargin = 1;
            int bottonMargin = 30;
            int rightMargin=15;
            tableRowParams.setMargins(leftMargin,topMargin, rightMargin, bottonMargin);
            row.setLayoutParams(tableRowParams);

            TextView productName = new TextView(this);
            productName.setText(fridgeItem.getItemName());
            productName.setTextSize(20);

            TextView expirationDate = new TextView(this);
            expirationDate.setText(fridgeItem.getExpirationDate());
            expirationDate.setTextSize(20);

            Button button = new Button(this);
            button.setText("Remove Food");
            button.setOnClickListener(v -> {
                viewModel.delete(fridgeItem);
            });
            row.addView(productName);
            row.addView(expirationDate);
            row.addView(button);

            tableLayout.addView(row);
        }
    }
}