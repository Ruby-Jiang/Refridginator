package com.refridginator.app.ui;

<<<<<<< Updated upstream
=======
import androidx.annotation.Nullable;
>>>>>>> Stashed changes
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< Updated upstream
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
=======
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
>>>>>>> Stashed changes
import android.widget.TextView;

import com.refridginator.app.R;
import com.refridginator.app.data.Item;

import java.util.ArrayList;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class AddGrocery extends AppCompatActivity {

<<<<<<< Updated upstream
    private Button btn_add;
    private LinearLayout container;

=======
    ArrayList<Item> items;
    private int itemQuantity;
    //    private Item[] items = new Item[]{new Item("afasf",2)};
    private static final String TAG = "GroceryListAct";
    private GrolistAdapter glAdapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    public void get_set_ItemQuantity(){
        itemQuantity=0;
        for (int i=0; i< items.size(); i++){
            itemQuantity += items.get(i).getNumber();
        }
        TextView quantity_tv = findViewById(R.id.textView4);
        quantity_tv.setText(itemQuantity+"");
    }
>>>>>>> Stashed changes

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
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
        child.setFocusable(true);
        child.setFocusableInTouchMode(true);
        child.requestFocus();
//        child.setBackgroundColor(Color.parseColor("#00ffff"));
        child.setTextSize(25);
        child.setPadding(10,0,0,10);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) current_edittext.getLayoutParams();
        params.setMargins(10,5,5,10);
        child.setLayoutParams(params);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(20);
        drawable.setColor(Color.parseColor("#00ffff"));
        child.setBackground(drawable);
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
=======
        setContentView(R.layout.activity_recycle_view);

        items = new ArrayList<>();
//        items.add(new Item("apple", 2));
//        items = new Item[]{new Item("sada", 9)};
//        items.add(0, new Item("sada", 9));
        recyclerView = this.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        glAdapter = new GrolistAdapter(this, items);
//
        recyclerView.setAdapter(glAdapter);
        recyclerView.setLayoutManager(layoutManager);

        View addItemButton = findViewById(R.id.btn_add_item);
//        addItemButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(GroceryListAct.this, RecycleViewAct.class));
//            }
//        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inputServer = new EditText(AddGrocery.this);
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddGrocery.this);
                builder.setTitle("Write down your wish item").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                        .setNegativeButton("Cancel", null);
                recyclerView.removeView(inputServer);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int fillPosition = items.size();
                        String inout = inputServer.getText().toString();
                        items.add(fillPosition, new Item(inout, 1));
                        glAdapter.notifyItemInserted(fillPosition);
                        get_set_ItemQuantity();
                    }
                });
                builder.show();
            }
        });

        glAdapter.setBtnDelListener(new GrolistAdapter.BtnOnClickListerner() {
            @Override
            public void onDeleteClick(int postion) {
                items.remove(postion);
                glAdapter.notifyItemRangeRemoved(postion, 1);
                get_set_ItemQuantity();
            }

            @Override
            public void onAddQuantityClick(int position) {
//                Toast.makeText(GroceryListAct.this, position+"", Toast.LENGTH_SHORT).show();
                Item e = items.get(position);
                e.setName(e.getName());
                e.setNumber(e.getNumber()+1);
                glAdapter.notifyItemChanged(position);
                get_set_ItemQuantity();
            }
        });
>>>>>>> Stashed changes
    }
}