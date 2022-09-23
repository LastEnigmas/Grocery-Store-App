package com.example.ariagrocery.dataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ariagrocery.GrocerItem;

import java.util.ArrayList;

@Database(entities = {GrocerItem.class,CartItems.class},version = 1)
public abstract class ShopDataBase extends RoomDatabase {
    public abstract GrocerItemDAO groceryItemDAO();
    public abstract CartItemDAO cartItemDAO();

    private static ShopDataBase instance;

    public static synchronized ShopDataBase getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context,ShopDataBase.class,"shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new initASYNC(instance).execute();
        }
    };

    private static class initASYNC extends AsyncTask<Void,Void,Void>{
        private GrocerItemDAO groceryitemdao;


        public initASYNC(ShopDataBase db) {
            this.groceryitemdao=db.groceryItemDAO();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<GrocerItem> allitems = new ArrayList<>();
            GrocerItem milk = new GrocerItem("Milk",
                    "Our rich and creamy fairlife 2% Ultrafiltered Partly Skimmed Milk has 50% less sugar and 50% more protein than regular milk, while remaining incredibly delicious. Plus, it’s lactose free!",
                    "https://www.denardi.ca/media/1_44328.jpg", "Drink", 5.17, 10);
            allitems.add(milk);

            GrocerItem soda = new GrocerItem("Soda",
                    "Whatever you call it, nothing compares to the refreshing, crisp taste of Coca-Cola®, the delicious soda you know and love. Enjoy with friends, on the go or with a meal. Whatever the occasion, wherever you are, Coca-Cola® makes life’s special moments a little bit better.",
                    "https://i5.walmartimages.ca/images/Enlarge/750/926/6000202750926.jpg", "Drink", 3.0, 30);
            allitems.add(soda);

            GrocerItem icecream = new GrocerItem("Ice Cream",
                    "Chocolate Ice Cream is a cool, creamy, classic treat with a delectably decadent and rich cocoa flavour. Made with real cocoa, and cream from 100% Canadian dairy, this ice cream parlour staple is a popular choice for any age.",
                    "https://i5.walmartimages.com/asr/3e6d6d0b-1f16-428d-a22e-0754ec3f9a9f_1.7f2023d79c221d4cf60c55faf1e0e1a2.jpeg", "Food", 6.0, 16);
            allitems.add(icecream);

            GrocerItem MTBMB = new GrocerItem("Music to Be Murdered By",
                    "Music to Be Murdered By is the eleventh studio album by American rapper Eminem. It was released on January 17, 2020, by Shady Records, Aftermath Entertainment, and Interscope Records.",
                    "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dw883ada85/products/L1011035/large/L1011035.JPG", "Music", 15.0, 30);
            allitems.add(MTBMB);

            GrocerItem Apple=new GrocerItem("Apple, Gala",
                    "The mild yet sweet flavour of the Gala apple has made it a family favourite for snacks, salads and sauces. The vibrant red mixes in with subtle hints of green to give the apple its unmistakable look.",
                    "https://i5.walmartimages.ca/images/Enlarge/094/514/6000200094514.jpg","Food",5.45,200);
            allitems.add(Apple);

            GrocerItem protein=new GrocerItem("Whey Protein Powder",
                    "Want incredible taste with a truly thick and creamy texture? Need a protein that will help you reach your goals while being economical? Look no further than ALLWHEY Classic! A true protein blend using only Whey Protein that delivers 30g of guaranteed, pure and tested protein in EVERY absolutely delicious 43g serving!!",
                    "https://i5.walmartimages.ca/images/Enlarge/834/482/6000199834482.jpg","Supplement",45.5,150);
            allitems.add(protein);

            GrocerItem TES=new GrocerItem("The Eminem Show",
                    "The Eminem Show is the fourth studio album by American rapper Eminem.",
                    "https://m.media-amazon.com/images/I/61tQIiY0zSL._SX466_.jpg","Music",14,30);
            allitems.add(TES);

            GrocerItem caffeine=new GrocerItem("Caffeine Ultra Pure",
                    "Change the game with 100% MuscleTech  Caffeine! This potent product is designed to fight fatigue, elevate alertness and increase intensity. You can maximize your workouts and tackle everyday projects with increased energy and focus!",
                    "https://i5.walmartimages.ca/images/Enlarge/286/281/6000202286281.jpg","Supplement" ,10.97,10);
            allitems.add(caffeine);

            GrocerItem icetea=new GrocerItem("Iced Tea Lemon",
                    "Fresh NESTEA Iced Tea Lemon Bottle 1.89 L","https://i5.walmartimages.ca/images/Enlarge/174/015/055000174015.jpg","Drink",2.47,50);

            allitems.add(icetea);

            GrocerItem premierprot=new GrocerItem("Premier Protein",
                    "Healthy protein can be hard to find. That’s why all of our delicious products are high in protein without all the added calories, sugar and fat. So whether it’s breakfast on the run, a mid-afternoon snack or a post-workout boost, you’ll get the energy you need every day.",
                    "https://i5.walmartimages.ca/images/Enlarge/566/845/6000201566845.jpg","Supplement",12,30);
            allitems.add(premierprot);


            GrocerItem diezelprot=new GrocerItem("DIESEL Whey Protein",
                    "New Zealand cows spend an average of 90% of their time on pasture and they get, on average 85% of their diet from grass, grass silage, hay and forage crops annually. Knowing that the Whey Protein in DIESEL comes from a natural environment that's ideal for growing grass, with fertile soil and abundant sunshine just feels good.",
                    "https://i5.walmartimages.ca/images/Enlarge/464/373/6000201464373.jpg","Supplement",36.97,100);
            allitems.add(diezelprot);

            GrocerItem prework=new GrocerItem("C4 Performance Powder",
                    "C4 Sport Blue Raspberry concentrated energy & performance dietary supplement powder provides a performance and energy boost that helps you in your journey towards becoming the best. To be your best, you need the energy and focus to perform at the highest level.",
                    "https://i5.walmartimages.ca/images/Enlarge/029/693/810390029693.jpg","Supplement",29.97,60);
            allitems.add(prework);

            for(GrocerItem g:allitems){
                groceryitemdao.insert(g);
            }




            return null;

        }
    }
}
