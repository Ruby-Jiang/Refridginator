package com.refridginator.app.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.refridginator.app.R;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class AddGrocery extends AppCompatActivity {

    private Button btn_add;
    private LinearLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);
        btn_add = findViewById(R.id.btn_add);
        container = findViewById(R.id.container);
        alertinfo("Hey look you made it to the grocery list", 1000);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (container instanceof ViewGroup) {
                    ViewGroup vp = (ViewGroup) container;
                    int index = vp.getChildCount();
                    EditText current_edittext = null;
                    //if index = 0. No elements in the container
                    if (index != 0){
                        //find the last EditText view
                        for (int i=index-1; i>=0; i--){
                            if (vp.getChildAt(i) instanceof EditText){
                                current_edittext =(EditText) vp.getChildAt(i);
                                break;
                            }
                        }
                    }
                    //add a new edittext view into the container
                    addView(current_edittext, index);
                }
            }
        });
    }

    protected void addView(EditText current_edittext, int index){
        //If curent edittext's text is empty, alert the user to fill in rather than create a new edittext view
        if (current_edittext != null) {
            if (TextUtils.isEmpty(current_edittext.getText())){
                alertinfo("Please fill in the item", 1000);
                return;
            }
        }
        EditText child = new EditText(this);
        child.setBackgroundColor(0);
//        child.setWidth();
        child.setSingleLine(true);
//        child.setTextSize(20);
//        child.setTextColor(getResources().getColor(R.color.colorAccent));
        // 获取当前的时间并转换为时间戳格式, 并设置给TextView
//        String currentTime = dateToStamp(System.currentTimeMillis());
        child.setHint("Please add an item");
//        child.setText(currentTime);
        // 调用一个参数的addView方法
        container.addView(child, index);
    }

    //A function to alert the users some infos
    protected void alertinfo(String alert_msg, int timedelay){
        final AlertDialog alert = new AlertDialog.Builder(AddGrocery.this)
                .create();
        Timer timer = new Timer();
        //make alert info dismiss
        TimerTask alert_to_fill = new TimerTask() {
            @Override
            public void run() { alert.dismiss(); }
        };
        alert.setMessage(alert_msg);
        alert.show();
        timer.schedule(alert_to_fill, timedelay);
    }
}