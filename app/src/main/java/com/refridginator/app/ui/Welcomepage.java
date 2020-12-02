package com.refridginator.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.refridginator.app.R;

import java.util.Timer;
import java.util.TimerTask;

public class Welcomepage extends AppCompatActivity {

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcomepage);

        actionBar=getActionBar();
//        actionBar.hide();

        Timer timer = new Timer();
        timer.schedule(Timertask1, 2000);
    }
        TimerTask Timertask1 = new TimerTask() {
            @Override
            public void run() {
                //登陆成功就返回主界面，不成功就留在登录界面
//                BmobUser bmobUser = BmobUser.getCurrentUser(BmobUser.class);
//                if (bmobUser != null){
////                    //登录成功
//                    startActivity(new Intent(Splash.this, MainActivity.class));
//                } else{
//                    //没有登录成功
//                    startActivity(new Intent(Splash.this, Login.class));
//                }
                startActivity(new Intent(Welcomepage.this, MainActivity.class));
//                startActivity(new Intent(Splash.this, Login.class));
                Welcomepage.this.finish();
            }
        };
}