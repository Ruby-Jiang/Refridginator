package com.refridginator.app;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;


public class AddAFood extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddAFood.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}