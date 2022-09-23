package com.example.ariagrocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FirstCardFragment extends Fragment implements CartItemAdapter.DeleteItem,CartItemAdapter.TotalPrice  {

    private TextView txtSum,txtNoItem;
    private Button btnNext;
    private CartItemAdapter adapter;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_cart_fragment,container,false);

        initView(view);

        adapter=new CartItemAdapter(this,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<GrocerItem> cartitems=Utils.getCartItems(getActivity());
        if (cartitems != null) {
            if(cartitems.size()>0){
                txtNoItem.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                adapter.setItems(cartitems);
            }else {
                txtNoItem.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
            }

        }else {
            txtNoItem.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container1,new SecondCartFragment());
                transaction.commit();
            }
        });


        return view;
    }

    private void initView(View view){
        txtSum= (TextView) view.findViewById(R.id.txttotalprice);
        txtNoItem= (TextView) view.findViewById(R.id.txtNoitem);
        btnNext= (Button) view.findViewById(R.id.btnNext);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView_cart_items);
        relativeLayout= view.findViewById(R.id.items_relativelayout);


    }

    @Override
    public void onDeleteResult(GrocerItem item) {
        Utils.deletefromCart(getActivity(),item);
        ArrayList<GrocerItem> cartitems=Utils.getCartItems(getActivity());
        if (cartitems != null) {
            if(cartitems.size()>0){
                txtNoItem.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                adapter.setItems(cartitems);
            }else {
                txtNoItem.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
            }

        }else {
            txtNoItem.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }


    }

    @Override
    public void getTotalPrice(double price) {
        txtSum.setText(String.valueOf(price)+"$");

    }
}
