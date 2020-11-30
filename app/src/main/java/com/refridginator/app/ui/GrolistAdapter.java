package com.refridginator.app.ui;

//import candroid.content.Context;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.refridginator.app.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refridginator.app.data.Item;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class GrolistAdapter extends RecyclerView.Adapter<GrolistAdapter.ItemHolder>{
    private static final String TAG = "GrolistAdapter";
    private ArrayList<Item> items;
    private Context context;
    private BtnOnClickListerner btnOnClickListener;


    //Constructor
    public GrolistAdapter(Context context, ArrayList<Item> items) {
        this.items = items;
        this.context = context;
    }

    //The getter of items.
    public ArrayList<Item> getItems() {
        return items;
    }

    //To setter of attribute "BtnOnClickListerner"
    public void setBtnDelListener(BtnOnClickListerner btnDelListener) {
        this.btnOnClickListener = btnDelListener;
    }

    public interface BtnOnClickListerner{
        void onDeleteClick(int postion);
        void onAddQuantityClick(int position);
    }

    //ViewHolder
    @NonNull
    @Override
    public GrolistAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ItemHolder(v, btnOnClickListener);
    }

    //onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position){
        if (this.items.get(position) == null){return;}
//        holder.itename.setText(this.items.get(position).getName());
        holder.quantity.setText(this.items.get(position).getNumber()+"");
        holder.deletebutton.setImageResource(R.drawable.trash);
        holder.addquantity.setImageResource(R.drawable.plus1);
        holder.x.setText("X");
//        showInputTips(holder.itename);

        if (holder.itename.getTag() instanceof TextWatcher){
            holder.itename.removeTextChangedListener ((TextWatcher) holder.itename.getTag());
        }
        String current_itemname = this.items.get(position).getName();
        holder.itename.setText(current_itemname);
        holder.itename.requestFocus();
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String trim = holder.itename.getText().toString().trim();
                items.get(position).setName(trim);
            }
        };
        holder.itename.addTextChangedListener(watcher);
        holder.itename.setTag(watcher);
    }

    //return the length of the arraylist items
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Self defined ViewHolder
    public static class ItemHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "ItemHolder";
        TextView itename;
        TextView quantity;
        ImageView deletebutton;
        ImageView addquantity;
        TextView x;

        public ItemHolder(@NonNull View itemView, final BtnOnClickListerner listerner) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: called");
            this.itename = itemView.findViewById(R.id.tv_itemname);
            this.quantity = itemView.findViewById(R.id.tv_quantity);
            this.deletebutton = itemView.findViewById(R.id.delete_button);
            this.addquantity = itemView.findViewById(R.id.btn_add_quantity);
            this.x = itemView.findViewById(R.id.tv_x);

            deletebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listerner.onDeleteClick(position);
                }
            });

            addquantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listerner.onAddQuantityClick(position);
                }
            });
        }
    }


    private void showInputTips(TextView et_text) {
        et_text.setFocusable(true);
        et_text.setFocusableInTouchMode(true);
        et_text.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) et_text.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et_text, 0);
    }

}
