package com.example.ariagrocery.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ariagrocery.GrocerItem;

import java.util.List;

@Dao
public interface CartItemDAO {

    @Query("INSERT INTO cart_items (groceryitemid) VALUES (:id) ;")
    void insert(int id);

    @Query("SELECT grocery_item.id,grocery_item.name,grocery_item.description,grocery_item.Category,grocery_item.imageUrl,"+
    "grocery_item.price,grocery_item.availableAmount,grocery_item.rate,grocery_item.userpoint,grocery_item.popularitypoint from grocery_item "+
    "Inner join cart_items on cart_items.groceryitemid=grocery_item.id")
    List<GrocerItem> getCartItems();

    @Query("DELETE FROM cart_items")
    void ClearCart();

}
