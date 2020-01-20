package com.ivanilov.zennex.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.ivanilov.zennex.Model.Preferences;
import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Fragment.ListItemFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Диалог, вызываемый при длительном нажатии на элементы в ListItemFragment
 * @autor Герман Иванилов
 * @version 1.0
 */

public class DialogListDelete extends DialogFragment {


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_list_delete, null);
        final Context context = view.getContext();


        Button buttonDelete = view.findViewById(R.id.Dialog_List_Delete_Delete);
        Button buttonEdit = view.findViewById(R.id.Dialog_List_Delete_Edit);


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogListAdd dialogListAdd = new DialogListAdd();
                dialogListAdd.setArguments(getArguments());
                dialogListAdd.show(getFragmentManager(), "");
                dismiss();
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preferences preferences = new Preferences();
                ArrayList<String> item = preferences.getArrayStringPreferences(getContext(), "ArrayItemList");
                List<Boolean> booleanItem = preferences.getArrayBooleanPreferences(getContext(), "ArrayBooleanList");

                item.remove(getArguments().getInt("Position"));
                booleanItem.remove(getArguments().getInt("Position"));


                preferences.setArrayStringPreferences(context, "ArrayItemList", item);
                preferences.setArrayBooleanPreferences(context, "ArrayBooleanList", booleanItem);

                ((ListItemFragment) getActivity().getSupportFragmentManager().getFragments().get(0)).refreshAdapter();

                dismiss();


            }
        });


        builder.setView(view);
        return builder.create();
    }


}
