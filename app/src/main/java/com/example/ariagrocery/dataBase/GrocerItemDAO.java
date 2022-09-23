package com.example.ariagrocery.dataBase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ariagrocery.GrocerItem;

import java.util.List;

@Dao
public interface GrocerItemDAO {

    @Insert
    void insert(GrocerItem grocerItem);

    @Query("SELECT * FROM grocery_item")
    List<GrocerItem> getAllitems();

    @Query("UPDATE grocery_item SET rate=:newrate WHERE id=:id")
    void updateRate(int id,int newrate);



    @Query("SELECT * FROM grocery_item WHERE id=:id")
    GrocerItem getItembyid (int id);

    @Query("Update grocery_item set reviews=:reviews where id=:id")
    void UpdateRevies(int id,String reviews);

    @Query("SELECT * from grocery_item where name like :text")
    List<GrocerItem> searchForItem(String text);

    @Query("SELECT DISTINCT category from grocery_item")
    List<String> getCategories();

    @Query("SELECT * FROM grocery_item WHERE category=:category")
    List<GrocerItem> getByCategory(String category);

    @Query("DELETE FROM cart_items WHERE groceryitemid=:id")
    void DeleteItembyID(int id);

    @Query("UPDATE grocery_item SET popularitypoint=:points WHERE id=:id")
    void increasepopularity(int points,int id);

    @Query("UPDATE grocery_item SET userpoint=:points WHERE id=:id")
    void updateuserpoint(int points,int id);

}
