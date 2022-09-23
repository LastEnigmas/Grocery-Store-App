package com.example.ariagrocery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    public interface DeleteItem{
        void onDeleteResult(GrocerItem item);
    }

    public interface TotalPrice{
        void getTotalPrice(double price);
    }

    private DeleteItem deleteItem;

    private TotalPrice totalPrice;

    private ArrayList<GrocerItem> items=new ArrayList<>();
    private Fragment fragment;

    public CartItemAdapter(Fragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtName.setText(items.get(position).getName());
        holder.txtPrice.setText(items.get(position).getPrice()+"$");
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context).setTitle("Deleting..").setMessage("Are you sure you want to delete this item?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try{
                                    deleteItem=(DeleteItem) fragment;
                                    deleteItem.onDeleteResult(items.get(position));

                                }catch (ClassCastException e){
                                    e.printStackTrace();
                                }


                            }
                        });
                builder.create().show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void calculatetotal(){
        double price=0;

        for (GrocerItem i: items){
            price+=i.getPrice();
        }

        price=Math.round(price*100.0)/100.0;



        try {
            totalPrice=(TotalPrice) fragment;
            totalPrice.getTotalPrice(price);

        }catch (ClassCastException e){
            e.printStackTrace();
        }

    }

    public void setItems(ArrayList<GrocerItem> items) {
        this.items = items;
        calculatetotal();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName,txtDelete,txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName= (TextView) itemView.findViewById(R.id.txtName);
            txtDelete= (TextView) itemView.findViewById(R.id.txtDelete);
            txtPrice= (TextView) itemView.findViewById(R.id.txtPrice);
        }
    }
}
