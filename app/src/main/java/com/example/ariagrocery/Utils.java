package com.example.ariagrocery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.RequiresApi;

import com.example.ariagrocery.dataBase.ShopDataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static int ORDER_ID=0;





    public static void ChangeRate(Context context, int id, int newRate) {
        ShopDataBase.getInstance(context).groceryItemDAO().updateRate(id,newRate);

    }


    public static int getOrderId(){
        ORDER_ID++;
        return ORDER_ID;
    }



    public static ArrayList<GrocerItem> getitemsbycategory(Context context,String category){
        return (ArrayList<GrocerItem>) ShopDataBase.getInstance(context).groceryItemDAO().getByCategory(category);
    }

    public static void addReview(Context context, Review review) {

        GrocerItem item=ShopDataBase.getInstance(context).groceryItemDAO().getItembyid(review.getGroceryId());
        ArrayList<Review> reviews= item.getReviews();
        if (reviews == null) {
            reviews=new ArrayList<>();

        }

        reviews.add(review);

        Gson gson=new Gson();
        String text= gson.toJson(reviews);
        ShopDataBase.getInstance(context).groceryItemDAO().UpdateRevies(item.getId(),text);



    }

    public static ArrayList<GrocerItem> getAllItem(Context context) {
        return (ArrayList<GrocerItem>) ShopDataBase.getInstance(context).groceryItemDAO().getAllitems();

    }

    public static ArrayList<Review> getreviewsbyid(Context context, int id) {


        return ShopDataBase.getInstance(context).groceryItemDAO().getItembyid(id).getReviews();


    }

    public static void additemtocart(Context context, GrocerItem item) {

        ShopDataBase.getInstance(context).cartItemDAO().insert(item.getId());


    }

    public static ArrayList<GrocerItem> getCartItems(Context context) {
        return (ArrayList<GrocerItem>) ShopDataBase.getInstance(context).cartItemDAO().getCartItems();

    }




    public static ArrayList<GrocerItem> searchForItem(Context context, String text) {
      //  ArrayList<GrocerItem> allitems = getAllItem(context);
    //    if (allitems != null) {
//            ArrayList<GrocerItem> items = new ArrayList<>();
//
//            for (GrocerItem item : allitems) {
//                if (item.getName().equalsIgnoreCase(text)) {
//                    items.add(item);
//                }
//
//                String[] names = item.getName().split(" ");
//
//                for (int o = 0; o < names.length; o++) {
//
//                    if (text.equalsIgnoreCase(names[o])) {
//                        boolean doesExist = false;
//
//                        for (GrocerItem i : items) {
//                            if (i.getId() == item.getId()) {
//                                doesExist = true;
//                            }
//
//
//                        }
//                        if (!doesExist) {
//                            items.add(item);
//                        }
//
//                    }
//                }
//
//
//
//
//
//            }
//            return items;
        String finaltxt="%"+text+"%";
        return (ArrayList<GrocerItem>) ShopDataBase.getInstance(context).groceryItemDAO().searchForItem(finaltxt);



    }

    public  static ArrayList<String> getCategories(Context context){
        return (ArrayList<String>) ShopDataBase.getInstance(context).groceryItemDAO().getCategories();
    }

    public static void deletefromCart(Context context,GrocerItem item){
        ShopDataBase.getInstance(context).groceryItemDAO().DeleteItembyID(item.getId());
    }

    public static void ClearCartItems(Context context){
        ShopDataBase.getInstance(context).cartItemDAO().ClearCart();
        }

    public static void increasePopularity(Context context,GrocerItem item,int points){

        int newPoint= item.getPopularitypoint()+points;
        ShopDataBase.getInstance(context).groceryItemDAO().increasepopularity(newPoint, item.getId());



    }

    public static void ChangeUserPoint(Context context,GrocerItem item,int point){

        int newp= item.getUserpoint()+point;
        ShopDataBase.getInstance(context).groceryItemDAO().updateuserpoint(newp, item.getId());
    }






}






