package com.example.ariagrocery.dataBase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItems {
    @PrimaryKey
    private int id;
    private int groceryitemid;

    public CartItems(int groceryitemid) {
        this.groceryitemid = groceryitemid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getGroceryitemid() {
        return groceryitemid;
    }
}
