package com.example.ariagrocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class mainFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView newitems,suggitems,popitems;

    private GroceryItemAdapter newItemadapter,popularitemadapter,suggestedItemadapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);

        initview(view);
        initBottomNavView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecViews();
    }

    private void initRecViews() {

        newItemadapter=new GroceryItemAdapter(getActivity());
        newitems.setAdapter(newItemadapter);
        newitems.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        popularitemadapter=new GroceryItemAdapter(getActivity());
        popitems.setAdapter(popularitemadapter);
        popitems.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        suggestedItemadapter=new GroceryItemAdapter(getActivity());
        suggitems.setAdapter(suggestedItemadapter);
        suggitems.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<GrocerItem> newItemslist= Utils.getAllItem(getActivity());
        if (newItemslist != null) {
            Comparator<GrocerItem> newItemscomparater=new Comparator<GrocerItem>() {
                @Override
                public int compare(GrocerItem grocerItem, GrocerItem t1) {
                    return grocerItem.getId()- t1.getId();
                }
            };

            Comparator<GrocerItem> reversecomparator=Collections.reverseOrder(newItemscomparater);
            Collections.sort(newItemslist,reversecomparator);


            ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP|ItemTouchHelper.DOWN,0) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    int from=viewHolder.getBindingAdapterPosition();
                    int to=target.getBindingAdapterPosition();

                    Collections.swap(newItemslist,from,to);


                    newItemadapter.notifyItemMoved(from,to);

                    return true;

                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                }
            });

            helper.attachToRecyclerView(newitems);
            newItemadapter.setItems(newItemslist);

        }


        ArrayList<GrocerItem> popularitems= Utils.getAllItem(getActivity());
        if (popularitems != null) {

            Comparator<GrocerItem> popularItemscomparater= new Comparator<GrocerItem>() {
                @Override
                public int compare(GrocerItem grocerItem, GrocerItem t1) {
                    return grocerItem.getPopularitypoint() - t1.getPopularitypoint();
                }
            };
            Collections.sort(popularitems,Collections.reverseOrder(popularItemscomparater));
            popularitemadapter.setItems(popularitems);

        }

        ArrayList<GrocerItem> suggesteditems= Utils.getAllItem(getActivity());
        if (suggesteditems != null) {

            Comparator<GrocerItem> suggestedItemscomparater= new Comparator<GrocerItem>() {
                @Override
                public int compare(GrocerItem grocerItem, GrocerItem t1) {
                    return grocerItem.getUserpoint()- t1.getUserpoint();
                }
            };
            Collections.sort(suggesteditems,Collections.reverseOrder(suggestedItemscomparater));
            suggestedItemadapter.setItems(suggesteditems);

        }









    }





    private void initview(View view) {
        bottomNavigationView= view.findViewById(R.id.bottomnavview);
        newitems= (RecyclerView) view.findViewById(R.id.newitems);
        suggitems= (RecyclerView) view.findViewById(R.id.suggesteditems);
        popitems= (RecyclerView) view.findViewById(R.id.populitems);
    }
    public void initBottomNavView(){
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        break;
                    case R.id.search:
                        Intent intent=new Intent(getActivity(),Search_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                        break;
                    case R.id.cart:
                        Intent cartintent=new Intent(getActivity(),CartActivity.class);
                        cartintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(cartintent);
                    default:
                        break;
                }
                return false;
            }
        });
    }

}
