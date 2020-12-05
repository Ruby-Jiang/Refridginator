package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.util.TableInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.refridginator.app.R;
import com.refridginator.app.data.FridgeItem;
import com.refridginator.app.viewmodels.StorageViewModel;

import org.w3c.dom.Text;

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

//         Test data
//        FridgeItem fridgeItem = new FridgeItem();
//        fridgeItem.setItemName("bread");
//        fridgeItem.setExpirationDate("2020-1-1");
//        viewModel.insert(fridgeItem);
//
//        fridgeItem = new FridgeItem();
//        fridgeItem.setItemName("milk dskdjhfsdkfhskdjfhskfhskdjfskdfhskdjfhkjsdfhksdhf");
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
            row.setBackgroundResource(R.drawable.row_border);
            //this code sets the parameters for each table row as they are generated
            //it provides extra spacing between the rows for better readability
            TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
            int leftMargin=15;
            int topMargin = 1;
            int bottonMargin = 30;
            int rightMargin=15;
            tableRowParams.setMargins(leftMargin,topMargin, rightMargin, bottonMargin);
            row.setLayoutParams(tableRowParams);

            TextView productName = new TextView(this);
            productName.setText(fridgeItem.getItemName());
            productName.setTextSize(25);
//            productName.setGravity(Gravity.CENTER);

            TextView expirationDate = new TextView(this);
            expirationDate.setText(" " + fridgeItem.getExpirationDate());
            expirationDate.setTextSize(20);

            Button button = new Button(this);
            button.setText("Remove");
            button.setOnClickListener(v -> {
                viewModel.delete(fridgeItem);
            });
            button.setWidth(80);
            row.addView(expirationDate);
            row.addView(button);

            TableRow productNameRow = new TableRow(this);
            productNameRow.addView(productName);


            tableLayout.addView(productNameRow);
            TableRow.LayoutParams params = (TableRow.LayoutParams) productName.getLayoutParams();
            params.span = 2;
            productName.setLayoutParams(params);
            tableLayout.addView(row);
        }
    }
}