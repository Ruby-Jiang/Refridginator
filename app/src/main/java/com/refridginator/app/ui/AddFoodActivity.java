package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refridginator.app.R;

import com.refridginator.app.api.OpenFoodFactsResponseModel;
import com.refridginator.app.viewmodels.StorageViewModel;
import com.refridginator.app.data.FridgeItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@AndroidEntryPoint
public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button scanBtn;
    StorageViewModel viewModel;
    androidx.appcompat.app.AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(StorageViewModel.class);
        scanCode();
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    public void Expiration(){
        View expirationDialog = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        EditText expirationDate = (EditText) expirationDialog.findViewById(R.id.edit_expiration);
        expirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDailog();
            }
        });
        dialog = new AlertDialog.Builder(this)
        .setTitle("Expiration Date Input")
        .setView(expirationDialog)
        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText edtText = findViewById(R.id.plain_text_input);
                EditText edtText1 = findViewById(R.id.plain_text_input1);
                String productName = edtText.getText().toString();
                String expirationDate = edtText1.getText().toString();
                FridgeItem fridgeItem = new FridgeItem();
                fridgeItem.setItemName(productName);
                fridgeItem.setExpirationDate("Best before " + expirationDate);
                viewModel.insert(fridgeItem);
                scanCode();
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText edtText = findViewById(R.id.plain_text_input);
                String productName = edtText.getText().toString();
                FridgeItem fridgeItem = new FridgeItem();
                fridgeItem.setItemName(productName);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateFormat.format(calendar.getTime());
                fridgeItem.setExpirationDate("Added at " + date);
                viewModel.insert(fridgeItem);
                scanCode();
            }
        })
        .show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                viewModel.searchByBarcode("8008698007389").subscribe(new Observer<OpenFoodFactsResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(OpenFoodFactsResponseModel target) {
                        EditText edtText = findViewById(R.id.plain_text_input);
                        edtText.setText(target.getProduct().getProductName());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Expiration Date", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Expiration();
                    }
                }).setNegativeButton("Abandon", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this,"No Results", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void showDatePickerDailog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "-" + month + "-" + dayOfMonth;
        TextView messageView = dialog.findViewById(R.id.edit_expiration);
        messageView.setText(date);
    }
}