package com.refridginator.app.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refridginator.app.R;
import com.refridginator.app.data.Item;

import java.util.ArrayList;

public class GrolistAdapter extends RecyclerView.Adapter<GrolistAdapter.ItemHolder>{
    private ArrayList<Item> items;
//    private Item[] items;
//    private int delimgsrc = R.drawable.trash;
//    private int addquantitysrc = R.drawable.plus1;
    private Context context;
    private BtnOnClickListerner btnDelListener;

    public void setBtnDelListener(BtnOnClickListerner btnDelListener) {
        this.btnDelListener = btnDelListener;
    }

    public interface getItem{
        String getItemIput(String iteminput);
    }

    public interface BtnOnClickListerner{
        void onDeleteClick(int postion);
        void onAddQuantityClick(int position);
    }
    public GrolistAdapter(Context context, ArrayList<Item> items) {
        this.items = items;
        this.context = context;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @NonNull
    @Override
    public GrolistAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ItemHolder(v, btnDelListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position){
        if (this.items.get(position) == null){return;}
        holder.itename.setText(this.items.get(position).getName());
        holder.quantity.setText(this.items.get(position).getNumber()+"");
        holder.deletebutton.setImageResource(R.drawable.trash);
        holder.addquantity.setImageResource(R.drawable.plus1);
        holder.x.setText("X");

        holder.itename.setFocusable(true);
        holder.itename.requestFocus();
        holder.itename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ItemIput = s.toString();
//                int index = holder.getAdapterPosition();
////                if (items.get(index) == null){
////                    items.set(index, new Item(ItemIput,1));
////                    return;
////                }
//                Toast.makeText(context, index+"", Toast.LENGTH_LONG).show();
//                items.add(index, new Item(ItemIput, Integer.parseInt((String) holder.quantity.getText())));
//                items.set(holder.getAdapterPosition(), ItemIput);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String ItemIput = s.toString();
//                int index = holder.getAdapterPosition();
////                if (items.get(index) == null){
////                    items.set(index, new Item(ItemIput,1));
////                    return;
////                }
//                Toast.makeText(context, index+"", Toast.LENGTH_LONG).show();
//                items.add(index, new Item(ItemIput, Integer.parseInt((String) holder.quantity.getText())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

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

}
