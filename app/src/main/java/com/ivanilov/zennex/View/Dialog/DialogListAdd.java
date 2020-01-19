package com.ivanilov.zennex.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.ivanilov.zennex.Model.Preferences;
import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Fragment.ListItemFragment;

import java.util.ArrayList;
import java.util.List;

public class DialogListAdd extends DialogFragment {

    String name;


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_list_add, null);
        final Context context = view.getContext();

        this.setCancelable(false);
        final EditText editText = view.findViewById(R.id.Dialog_List_Add_Edit);
        Button buttonOk = view.findViewById(R.id.Dialog_List_Add_Ok);
        Button buttonCancel = view.findViewById(R.id.Dialog_List_Add_Cancel);

        try {
            name = getArguments().getString("Name");
        } catch (NullPointerException e) {
            name = "";
        }

        editText.setText(name);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) {
                    Toast.makeText(getContext(), "Введите название элемента", Toast.LENGTH_LONG).show();
                } else {
                    Preferences preferences = new Preferences();
                    ArrayList<String> item = preferences.getArrayStringPreferences(getContext(), "ArrayItemList");
                    List<Boolean> booleanItem = preferences.getArrayBooleanPreferences(getContext(), "ArrayBooleanList");

                    if (item == null) {
                        item = new ArrayList<>();
                        booleanItem = new ArrayList<>();
                    }

                    if (getArguments().containsKey("Position")) {
                        item.set(getArguments().getInt("Position"), editText.getText().toString());
                    } else {
                        item.add(editText.getText().toString());
                        booleanItem.add(false);
                    }


                    preferences.setArrayStringPreferences(context, "ArrayItemList", item);
                    preferences.setArrayBooleanPreferences(context, "ArrayBooleanList", booleanItem);

                    ((ListItemFragment) getActivity().getSupportFragmentManager().getFragments().get(0)).refreshAdapter();

                    dismiss();

                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        builder.setView(view);
        return builder.create();
    }


}
