package com.example.ariagrocery;

import static com.example.ariagrocery.allCategoriesDialog.ALL_CATEGORIES;
import static com.example.ariagrocery.allCategoriesDialog.CALLING_ACTIVITY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Search_Activity extends AppCompatActivity implements allCategoriesDialog.GetCategory {

    private MaterialToolbar toolbar;
    private EditText searchBox;
    private TextView firstcat,secondcat,thirdcat,allcat;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ImageView btnSearch;

    private GroceryItemAdapter adapter;
   private ArrayList<GrocerItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initBottomNavView();

        setSupportActionBar(toolbar);

        adapter=new GroceryItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        Intent intent=getIntent();
        if (intent != null) {
            String category=intent.getStringExtra("category");
            if(category!=null){
                items=Utils.getitemsbycategory(this,category);
                if (items!=null){
                    adapter.setItems(items);

                    increaseuserpoint(items);
                }
            }


        }






        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initsearch();
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initsearch();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ArrayList<String> categories=Utils.getCategories(this);
        if (categories != null) {
            if(categories.size()>0){

                if(categories.size()==1){
                    showCategories(categories,1);
                }else if (categories.size()==2){
                    showCategories(categories,2);

                }else {
                    showCategories(categories,3);

                }


            }

        }

        allcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCategoriesDialog dialog= new allCategoriesDialog();
                Bundle bundle=new Bundle();
                bundle.putStringArrayList(ALL_CATEGORIES,Utils.getCategories(Search_Activity.this));
                bundle.putString(CALLING_ACTIVITY,"search");
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(),"all categories");
            }
        });






    }

    private void showCategories(ArrayList<String> categories, int i) {
        switch (i){
            case 1:
                firstcat.setVisibility(View.VISIBLE);
                firstcat.setText(categories.get(0));
                secondcat.setVisibility(View.GONE);
                thirdcat.setVisibility(View.GONE);
                firstcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<GrocerItem> items=Utils.getitemsbycategory(Search_Activity.this,categories.get(0));
                        if (items != null) {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }

                    }
                });
                break;
            case 2:
                firstcat.setVisibility(View.VISIBLE);
                firstcat.setText(categories.get(0));
                secondcat.setVisibility(View.VISIBLE);
                secondcat.setText(categories.get(1));
                thirdcat.setVisibility(View.GONE);
                firstcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<GrocerItem> items=Utils.getitemsbycategory(Search_Activity.this,categories.get(0));
                        if (items != null) {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }

                    }
                });
                secondcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<GrocerItem> items=Utils.getitemsbycategory(Search_Activity.this,categories.get(1));
                        if (items != null) {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }

                    }
                });
                break;
            case 3:
                firstcat.setVisibility(View.VISIBLE);
                firstcat.setText(categories.get(0));
                secondcat.setVisibility(View.VISIBLE);
                secondcat.setText(categories.get(1));
                thirdcat.setVisibility(View.VISIBLE);
                thirdcat.setText(categories.get(2));
                firstcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<GrocerItem> items=Utils.getitemsbycategory(Search_Activity.this,categories.get(0));
                        if (items != null) {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }

                    }
                });
                secondcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<GrocerItem> items=Utils.getitemsbycategory(Search_Activity.this,categories.get(1));
                        if (items != null) {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }

                    }
                });
                thirdcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ArrayList<GrocerItem> items=Utils.getitemsbycategory(Search_Activity.this,categories.get(2));
                        if (items != null) {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }

                    }
                });
                break;

            default:
                secondcat.setVisibility(View.GONE);
                thirdcat.setVisibility(View.GONE);
                firstcat.setVisibility(View.GONE);

                break;

        }
    }

    private void initsearch() {
        if(!searchBox.getText().toString().equals("")){
            //TODO: get items
            String name=searchBox.getText().toString().toLowerCase();
            ArrayList<GrocerItem> items=Utils.searchForItem(this,name);
            if (items != null) {
                adapter.setItems(items);
                increaseuserpoint(items);

            }

        }


    }

    private void initViews(){
        toolbar= (MaterialToolbar) findViewById(R.id.toolbar);
        searchBox= (EditText) findViewById(R.id.searchBox);
        btnSearch= (ImageView) findViewById(R.id.btnsearc);
        firstcat= (TextView) findViewById(R.id.txtfirst_category);
        secondcat= (TextView) findViewById(R.id.txtsecond_category);
        thirdcat= (TextView) findViewById(R.id.txtthird_category);
        allcat= (TextView) findViewById(R.id.txtAllcategories);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottomnavview);
        recyclerView= (RecyclerView) findViewById(R.id.search_recycler);
    }

    public void initBottomNavView(){
        bottomNavigationView.setSelectedItemId(R.id.search);
        //TODO: finish this.
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent=new Intent(Search_Activity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.search:

                        break;
                    case R.id.cart:
                        Intent cartintent=new Intent(Search_Activity.this,CartActivity.class);
                        cartintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(cartintent);
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onGetCategoryResult(String category) {
        ArrayList<GrocerItem> items=Utils.getitemsbycategory(this,category);
        if (items != null) {
            adapter.setItems(items);
            increaseuserpoint(items);

        }

    }

    private void increaseuserpoint(ArrayList<GrocerItem> items){
        for (GrocerItem i:items){
            Utils.ChangeUserPoint(this,i,1);
        }
    }
}