package com.ivanilov.zennex.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ivanilov.zennex.Model.Preferences;
import com.ivanilov.zennex.Presenter.ListItemAdapter;
import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Dialog.DialogListAdd;

import java.util.ArrayList;
import java.util.List;


public class ListItemFragment extends Fragment{

    Preferences preferences;
    FloatingActionButton fab;
    ListView listView;
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);

        preferences = new Preferences();
        context = getContext();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();

        fab = this.getView().findViewById(R.id.fragment_list_fab);


        listView = getView().findViewById(R.id.fragment_list_list_view);

        refreshAdapter();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogListAdd dialogListAdd = new DialogListAdd();
                dialogListAdd.show(getFragmentManager(), "");

            }
        });

    }

    public void refreshAdapter() {

        ArrayList<String> item = preferences.getArrayStringPreferences(getContext(), "ArrayItemList");

        List<Boolean> booleanItem = preferences.getArrayBooleanPreferences(getContext(), "ArrayBooleanList");

        if (item != null) {
            ListItemAdapter adapter = new ListItemAdapter(this, item, booleanItem);
            listView.setAdapter(adapter);

        }

    }

    public void saveItemBoolean(List<Boolean> itemBoolean) {
        preferences.setArrayBooleanPreferences(context, "ArrayBooleanList", itemBoolean);
    }



    public interface OnFragmentInteractionListener {
    }





}