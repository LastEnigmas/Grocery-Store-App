package com.example.ariagrocery;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderEndpoint {

    @POST("posts")
    Call<Order> newOrder(@Body Order order);

}
