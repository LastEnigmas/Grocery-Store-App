package com.example.ariagrocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.android.material.appbar.MaterialToolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Grocery_item_activity extends AppCompatActivity implements AddReviewDialog.AddReview {
    private static final String TAG = "Grocery_item_activity";
    public static final String GROCERY_ITEM_KEY="GROCERYKEY";
    private boolean isBound;
    private TrackUserTime mService;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TrackUserTime.LocalBinder binder=(TrackUserTime.LocalBinder) iBinder;
            mService= binder.getService();
            isBound=true;
            mService.setItem(incomingitmem);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound=false;

        }
    };

    private Button btnaddtocart;
    private RecyclerView reviewsrecycler;
    private TextView txtName,txtDescription,txtaddareview,txtprice;
    private ImageView itemImage,first_empty_star,first_filled_star,second_empty_star,second_filled_star,third_empty_star,third_filled_star;
    private RelativeLayout first_star_layout,second_star_layout,third_star_layout;

    private GrocerItem incomingitmem;
    private MaterialToolbar toolbar;
    private ReviewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);
        initviews();
        setSupportActionBar(toolbar);

        adapter=new ReviewsAdapter();


        Intent intent=getIntent();
        if (intent != null) {
            incomingitmem=intent.getParcelableExtra(GROCERY_ITEM_KEY);
            if (incomingitmem != null) {

                Utils.ChangeUserPoint(this,incomingitmem,1);


                txtName.setText(incomingitmem.getName());
                txtDescription.setText(incomingitmem.getDescription());
                txtprice.setText(String.valueOf(incomingitmem.getPrice())+"$");
                Glide.with(this).asBitmap().load(incomingitmem.getImageUrl()).into(itemImage);

                ArrayList<Review> reviews=Utils.getreviewsbyid(this,incomingitmem.getId());

                reviewsrecycler.setAdapter(adapter);
                reviewsrecycler.setLayoutManager(new LinearLayoutManager(this));

                if (reviews != null) {
                    if(reviews.size()>0){


                        adapter.setReviews(reviews);
                    }

                }

                btnaddtocart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Utils.additemtocart(Grocery_item_activity.this,incomingitmem);
                        Intent intent4=new Intent(Grocery_item_activity.this,CartActivity.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent4);

                    }
                });

                txtaddareview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       AddReviewDialog dialog=new AddReviewDialog();
                       Bundle bundle=new Bundle();
                       bundle.putParcelable(GROCERY_ITEM_KEY,incomingitmem);
                       dialog.setArguments(bundle);
                       dialog.show(getSupportFragmentManager(),"add review");


                    }
                });

                handleRatings();



            }

        }
    }

    private void handleRatings() {
        switch (incomingitmem.getRate()){
            case 0:
                first_empty_star.setVisibility(View.VISIBLE);
                first_filled_star.setVisibility(View.GONE);
                second_empty_star.setVisibility(View.VISIBLE);
                second_filled_star.setVisibility(View.GONE);
                third_empty_star.setVisibility(View.VISIBLE);
                third_filled_star.setVisibility(View.GONE);

                break;

            case 1:

                first_empty_star.setVisibility(View.GONE);
                first_filled_star.setVisibility(View.VISIBLE);
                second_empty_star.setVisibility(View.VISIBLE);
                second_filled_star.setVisibility(View.GONE);
                third_empty_star.setVisibility(View.VISIBLE);
                third_filled_star.setVisibility(View.GONE);


                break;
            case 2:
                first_empty_star.setVisibility(View.GONE);
                first_filled_star.setVisibility(View.VISIBLE);
                second_empty_star.setVisibility(View.GONE);
                second_filled_star.setVisibility(View.VISIBLE);
                third_empty_star.setVisibility(View.VISIBLE);
                third_filled_star.setVisibility(View.GONE);
                break;
            case 3:

                first_empty_star.setVisibility(View.GONE);
                first_filled_star.setVisibility(View.VISIBLE);
                second_empty_star.setVisibility(View.GONE);
                second_filled_star.setVisibility(View.VISIBLE);
                third_empty_star.setVisibility(View.GONE);
                third_filled_star.setVisibility(View.VISIBLE);
                break;
            default:
                break;




        }

        first_star_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(incomingitmem.getRate()!=1){
                    Utils.ChangeRate(Grocery_item_activity.this,incomingitmem.getId(),1);
                    Utils.ChangeUserPoint(Grocery_item_activity.this,incomingitmem,(1- incomingitmem.getRate())*2);
                    incomingitmem.setRate(1);
                    handleRatings();

                }
            }
        });

        second_star_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(incomingitmem.getRate()!=2){
                    Utils.ChangeRate(Grocery_item_activity.this,incomingitmem.getId(),2);
                    Utils.ChangeUserPoint(Grocery_item_activity.this,incomingitmem,(2- incomingitmem.getRate())*2);
                    incomingitmem.setRate(2);
                    handleRatings();

                }
            }
        });

        third_star_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(incomingitmem.getRate()!=3){
                    Utils.ChangeRate(Grocery_item_activity.this,incomingitmem.getId(),3);
                    Utils.ChangeUserPoint(Grocery_item_activity.this,incomingitmem,(3- incomingitmem.getRate())*2);
                    incomingitmem.setRate(3);
                    handleRatings();

                }
            }
        });
    }

    private void initviews() {
        btnaddtocart= (Button) findViewById(R.id.btnaddtocart);
        reviewsrecycler= (RecyclerView) findViewById(R.id.review_recycler);
        txtName= (TextView) findViewById(R.id.txtName);
        txtDescription= (TextView) findViewById(R.id.txtdescription);
        txtaddareview= (TextView) findViewById(R.id.txtaddreview);
        txtprice= (TextView) findViewById(R.id.txtPrice);
        itemImage= (ImageView) findViewById(R.id.itemimg);
        first_empty_star= (ImageView) findViewById(R.id.firstemptystar);
        first_filled_star= (ImageView) findViewById(R.id.firstfilledstar);
        second_empty_star= (ImageView) findViewById(R.id.secondtemptystar);
        second_filled_star= (ImageView) findViewById(R.id.secondfilledstar);
        third_empty_star= (ImageView) findViewById(R.id.thirdemptystar);
        third_filled_star= (ImageView) findViewById(R.id.thirdfilledstar);
        first_star_layout= (RelativeLayout) findViewById(R.id.firststarrellayout);
        second_star_layout= (RelativeLayout) findViewById(R.id.secondstarrellayout);
        third_star_layout= (RelativeLayout) findViewById(R.id.thirdstarrellayout);
        toolbar= (MaterialToolbar) findViewById(R.id.toolbar);


    }

    @Override
    public void onAddReviewResult(Review review) {
        Utils.addReview(this,review);
        Utils.ChangeUserPoint(this,incomingitmem,3);
        ArrayList<Review> reviews=Utils.getreviewsbyid(this,review.getGroceryId());
        if (reviews != null) {
            adapter.setReviews(reviews);

        }



    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,TrackUserTime.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(connection);
        }
    }
}