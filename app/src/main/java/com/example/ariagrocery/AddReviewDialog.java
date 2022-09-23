package com.example.ariagrocery;

import static com.example.ariagrocery.Grocery_item_activity.GROCERY_ITEM_KEY;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReviewDialog extends DialogFragment {

    public interface AddReview{
        void onAddReviewResult(Review review);
    }



    private AddReview addreview;
    private TextView txtitemName,txtWarning;
    private EditText edtTxtName,edttxtreview;
    private Button btnnaddreview;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_add_review,null);
        initviews(view);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity()).setView(view);

        Bundle bundle=getArguments();
        if (bundle != null) {
           final GrocerItem item=bundle.getParcelable(GROCERY_ITEM_KEY);
            if (item != null) {
                txtitemName.setText(item.getName());
                btnnaddreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username=edtTxtName.getText().toString();
                        String review=edttxtreview.getText().toString();
                        String date=getCurrentDate();
                        if (username.equals("")||review.equals("")){
                            txtWarning.setText("Please fill all the blanks");
                            txtWarning.setVisibility(View.VISIBLE);
                        }else
                        {
                            txtWarning.setVisibility(View.GONE);
                            try {
                                addreview =(AddReview) getActivity();
                                addreview.onAddReviewResult(new Review(item.getId(), username,review,date));
                                dismiss();

                            }catch (ClassCastException e){
                                e.printStackTrace();
                            }

                        }
                    }
                });


            }
        }


        return builder.create();
    }

    private String getCurrentDate(){

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
        return sdf.format(calendar.getTime());


    }

    private void initviews(View view) {
        txtitemName= (TextView) view.findViewById(R.id.txtitemnamedialog);
        txtWarning= (TextView) view.findViewById(R.id.txtview_warning);
        edtTxtName= (EditText) view.findViewById(R.id.edttxtusername);
        edttxtreview= (EditText) view.findViewById(R.id.edttxtreview);
        btnnaddreview= (Button) view.findViewById(R.id.btnadd_comment);



    }
}
