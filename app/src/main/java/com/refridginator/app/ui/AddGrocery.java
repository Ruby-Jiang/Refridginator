package com.refridginator.app.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
import android.widget.TextView;

import com.refridginator.app.R;
import com.refridginator.app.data.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AddGrocery extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}