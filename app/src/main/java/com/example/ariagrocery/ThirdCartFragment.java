package com.example.ariagrocery;

import static com.example.ariagrocery.SecondCartFragment.ORDER_KEY;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThirdCartFragment extends Fragment {
    private static final String TAG = "ThirdCartFragment";
    private Button btnBack,btnCheckout;
    private TextView txtitems,txtAddress,txtPhonenumber,txtTotalPrice;
    private RadioGroup rgPayment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart_third,container,false);

        initViews(view);

        Bundle bundle=getArguments();
        if (bundle != null) {
            String jsonOrder= bundle.getString(ORDER_KEY);
            if(null!=jsonOrder){
                Gson gson=new Gson();
                Type type=new TypeToken<Order>(){}.getType();
                Order order=gson.fromJson(jsonOrder,type);
                if (order != null) {
                    String items="";
                    for(GrocerItem i: order.getItems()){
                        items +="\n\t"+i.getName();
                    }

                    txtitems.setText(items);
                    txtAddress.setText(order.getAddress());
                    txtPhonenumber.setText(order.getPhonenumber());
                    txtTotalPrice.setText(String.valueOf(order.getPrice()));

                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle backBundle=new Bundle();
                            backBundle.putString(ORDER_KEY,jsonOrder);
                            SecondCartFragment secondCartFragment=new SecondCartFragment();
                            secondCartFragment.setArguments(backBundle);
                            FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.container1,secondCartFragment);
                            transaction.commit();
                        }
                    });

                    btnCheckout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (rgPayment.getCheckedRadioButtonId()){
                                case R.id.rbPaypal:
                                    order.setPaymentmethod("Paypal");
                                    break;
                                case R.id.rbCreditcard:
                                    order.setPaymentmethod("Credit");
                                    break;
                                default:
                                   order.setPaymentmethod("Unknown");
                                   break;
                            }

                            order.setSuccess(true);

                            //todo: send the request to the server

                            HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
                            OkHttpClient client=new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

                            Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                                    .addConverterFactory(GsonConverterFactory.create()).client(client).build();

                            OrderEndpoint endpoint=retrofit.create(OrderEndpoint.class);
                            Call<Order> call= endpoint.newOrder(order);
                            call.enqueue(new Callback<Order>() {
                                @Override
                                public void onResponse(Call<Order> call, Response<Order> response) {
                                    Log.d(TAG, "onResponse: "+response.code());
                                    if (response.isSuccessful()){
                                        Bundle resultbundle=new Bundle();
                                        resultbundle.putString(ORDER_KEY, gson.toJson(response.body()));
                                        PaymentResult paymentResult=new PaymentResult();
                                        paymentResult.setArguments(resultbundle);
                                        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                                        transaction.replace(R.id.container1,paymentResult);
                                        transaction.commit();
                                    }else {
                                        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                                        transaction.replace(R.id.container1,new PaymentResult());
                                        transaction.commit();

                                    }
                                }

                                @Override
                                public void onFailure(Call<Order> call, Throwable t) {
                                    t.printStackTrace();

                                }
                            });
                        }
                    });



                }
            }

        }



        return view;
    }

    private void initViews(View view) {

        btnBack= (Button) view.findViewById(R.id.btnBack);
        btnCheckout= (Button) view.findViewById(R.id.btncheckout);
        txtitems= (TextView) view.findViewById(R.id.txtitem_names);
        txtAddress= (TextView) view.findViewById(R.id.txtaddress);
        txtPhonenumber= (TextView) view.findViewById(R.id.txtphonenum);
        txtTotalPrice= (TextView) view.findViewById(R.id.txtTotalPrice);
        rgPayment= (RadioGroup) view.findViewById(R.id.rgPaymentMethod);

    }
}
