package com.example.ariagrocery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class allCategoriesDialog extends DialogFragment {

    public interface GetCategory{
        void onGetCategoryResult(String category);
    }

    private GetCategory getCategory;
    public static final String ALL_CATEGORIES="categories";
    public static final String CALLING_ACTIVITY="categories";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_all_categories,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity()).setView(view);


        Bundle bundle=getArguments();
        if (bundle != null) {
            String callingActivity= bundle.getString(CALLING_ACTIVITY);
            ArrayList<String> Categories= Utils.getCategories(getActivity());
            if (Categories != null) {
                ListView listView=view.findViewById(R.id.listview);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,Categories);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (callingActivity){
                            case "main":
                                Intent intent=new Intent(getActivity(),Search_Activity.class);
                                intent.putExtra("category",Categories.get(i));
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().startActivity(intent);
                                dismiss();
                                break;
                            case "search":
                                try {
                                    getCategory=(GetCategory) getActivity();
                                    getCategory.onGetCategoryResult(Categories.get(i));
                                    dismiss();

                                }catch (ClassCastException e){
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                break;
                        }

                    }
                });



            }

        }

        return builder.create();
    }
}
