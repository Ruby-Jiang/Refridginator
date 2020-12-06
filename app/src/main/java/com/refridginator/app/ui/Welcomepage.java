package com.refridginator.app.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.refridginator.app.R;

import java.util.Timer;
import java.util.TimerTask;

public class Welcomepage extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        actionBar = getActionBar();

        Timer timer = new Timer();
        timer.schedule(Timertask1, 2000);
    }

    TimerTask Timertask1 = new TimerTask() {
        @Override
        public void run() {
            startActivity(new Intent(Welcomepage.this, MainActivity.class));
            Welcomepage.this.finish();
        }
    };
}