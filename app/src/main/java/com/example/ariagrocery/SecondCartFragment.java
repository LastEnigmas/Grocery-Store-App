package com.example.ariagrocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondCartFragment extends Fragment {
    private EditText edttxtAddress,edttxtZipcode,edttxtPhoneNumber,edttxtEmail;
    private Button btnNext,btnBack;
    private TextView txtWarning;

    public static final String ORDER_KEY="order";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart_second,container,false);

        initViews(view);

        Bundle bundle=getArguments();
        if (bundle != null) {
            String jsonorder= bundle.getString(ORDER_KEY);
            if (jsonorder != null) {
                Gson gson=new Gson();
                Type type=new TypeToken<Order>(){}.getType();
                Order order=gson.fromJson(jsonorder,type);
                if (order != null) {
                    edttxtAddress.setText(order.getAddress());
                    edttxtEmail.setText(order.getEmail());
                    edttxtZipcode.setText(order.getZipcode());
                    edttxtPhoneNumber.setText(order.getPhonenumber());

                }

            }

        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container1,new FirstCardFragment());
                transaction.commit();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valdidateData()){
                    txtWarning.setVisibility(View.GONE);
                    ArrayList<GrocerItem> cartItems=Utils.getCartItems(getActivity());
                    if (cartItems != null) {
                        Order order=new Order();
                        order.setItems(cartItems);
                        order.setAddress(edttxtAddress.getText().toString());
                        order.setEmail(edttxtEmail.getText().toString());
                        order.setPhonenumber(edttxtPhoneNumber.getText().toString());
                        order.setZipcode(edttxtZipcode.getText().toString());
                        order.setPrice(calculatetotal(cartItems));

                        Gson gson=new Gson();

                        String jsonorder=gson.toJson(order);
                        Bundle bundle=new Bundle();
                        bundle.putString(ORDER_KEY,jsonorder);


                        ThirdCartFragment thirdCartFragment=new ThirdCartFragment();
                        thirdCartFragment.setArguments(bundle);
                        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container1,thirdCartFragment);
                        transaction.commit();





                    }

                }else{
                    txtWarning.setVisibility(View.VISIBLE);
                    txtWarning.setText("Please fill all the blanks");
                }
            }
        });


        return view;
    }

    private boolean valdidateData() {
        if (edttxtEmail.getText().toString().equals("") || edttxtAddress.getText().toString().equals("")|| edttxtPhoneNumber.getText().toString().equals("")||edttxtZipcode.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    private double calculatetotal(ArrayList<GrocerItem> items){
        double price=0;

        for (GrocerItem i: items){
            price+=i.getPrice();
        }

        price=Math.round(price*100.0)/100.0;
        return price;



    }

    private void initViews(View view) {
        edttxtAddress= (EditText) view.findViewById(R.id.edttxtaddress);
        edttxtZipcode= (EditText) view.findViewById(R.id.edttxtzipcode);
        edttxtPhoneNumber= (EditText) view.findViewById(R.id.edttxtphonenum);
        edttxtEmail= (EditText) view.findViewById(R.id.edttxtemail);
        btnNext=  view.findViewById(R.id.btnNext);
        btnBack=view.findViewById(R.id.btnBack);
        txtWarning=view.findViewById(R.id.checkout_warning);
    }
}
