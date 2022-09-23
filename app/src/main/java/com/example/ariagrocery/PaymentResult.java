package com.example.ariagrocery;

import static com.example.ariagrocery.SecondCartFragment.ORDER_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PaymentResult extends Fragment
{

    private TextView txtMessage;
    private Button btnHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_payment_result,container,false);

        initviews(view);

        Bundle bundle=getArguments();
        if (bundle!=null){

            String jsonmOrder=bundle.getString(ORDER_KEY);
            if(jsonmOrder!=null){
                Gson gson=new Gson();
                Type type=new TypeToken<Order>(){}.getType();
                Order order=gson.fromJson(jsonmOrder,type);
                if (order != null) {
                    if(order.isSuccess()){
                        txtMessage.setText("Payment was successfull" );


                        //TODO: Increase user point

                        Utils.ClearCartItems(getActivity());

                        for(GrocerItem item: order.getItems()){
                            Utils.increasePopularity(getActivity(),item,1);
                            Utils.ChangeUserPoint(getActivity(),item,4);
                        }


                    }else {

                        txtMessage.setText("Payment failed,\nPlease try again." );
                    }


                }

            }else {
                txtMessage.setText("Payment failed,\nPlease try again." );


            }



        }


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent10=new Intent(getActivity(),MainActivity.class);
                intent10.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent10);
            }
        });

        return view;
    }

    private void initviews(View view) {

        txtMessage=view.findViewById(R.id.conshop);
        btnHome=view.findViewById(R.id.btnconshopping);


    }
}
