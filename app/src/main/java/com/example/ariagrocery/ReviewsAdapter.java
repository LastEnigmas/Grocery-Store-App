package com.example.ariagrocery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    ArrayList<Review> reviews=new ArrayList<>();

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public ReviewsAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtusername.setText(reviews.get(position).getUsername());
        holder.txtreview.setText(reviews.get(position).getText());
        holder.txtDate.setText(reviews.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtusername,txtreview,txtDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtusername=itemView.findViewById(R.id.txtUsername);
            txtreview=itemView.findViewById(R.id.reviewtxt);
            txtDate=itemView.findViewById(R.id.txtdate);

        }
    }


}
