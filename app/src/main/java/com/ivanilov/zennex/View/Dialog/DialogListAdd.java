package com.ivanilov.zennex.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.ivanilov.zennex.Model.Preferences;
import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Fragment.ListItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Диалог, используемый для создания и изменения элемнтов в ListItemFragment
 * @autor Герман Иванилов
 * @version 1.0
 */

public class DialogListAdd extends DialogFragment {

    String name;
    EditText editText;
    DialogListAdd dialogListAdd;
    ArrayList<String> item;
    List<Boolean> booleanItem;
    Preferences preferences;
    Context context;
    FragmentActivity fragmentActivity;


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_list_add, null);

        this.setCancelable(false);

        editText = view.findViewById(R.id.Dialog_List_Add_Edit);
        Button buttonOk = view.findViewById(R.id.Dialog_List_Add_Ok);
        Button buttonCancel = view.findViewById(R.id.Dialog_List_Add_Cancel);

        dialogListAdd = this;
        context = getContext();
        preferences = new Preferences();
        item = preferences.getArrayStringPreferences(getContext(), "ArrayItemList");
        booleanItem = preferences.getArrayBooleanPreferences(getContext(), "ArrayBooleanList");
        fragmentActivity = getActivity();


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
                    Toast.makeText(getContext(), getResources().getString(R.string.enter_title), Toast.LENGTH_LONG).show();
                } else {


                    if (item == null) {
                        item = new ArrayList<>();
                        booleanItem = new ArrayList<>();
                    }

                    if (getArguments() != null && getArguments().containsKey("Position")) {
                        item.set(getArguments().getInt("Position"), editText.getText().toString());
                    } else {
                        item.add(editText.getText().toString());
                        booleanItem.add(false);
                    }


                    preferences.setArrayStringPreferences(context, "ArrayItemList", item);
                    preferences.setArrayBooleanPreferences(context, "ArrayBooleanList", booleanItem);


                    ((ListItemFragment) fragmentActivity.getSupportFragmentManager().getFragments().get(0)).refreshAdapter();

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

    @Override
    public void onResume() {
        super.onResume();

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(final android.content.DialogInterface dialog,
                                 int keyCode, android.view.KeyEvent event) {
                if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {

                    if (editText.getText().toString().equals(name)) {
                        dismiss();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setTitle(getResources().getString(R.string.save) + " " + editText.getText().toString() + "?");

                        builder.setPositiveButton(getResources().getString(R.string.save), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (getArguments() != null && getArguments().containsKey("Position")) {
                                    item.set(getArguments().getInt("Position"), editText.getText().toString());
                                } else {
                                    item.add(editText.getText().toString());
                                    booleanItem.add(false);
                                    preferences.setArrayBooleanPreferences(context, "ArrayBooleanList", booleanItem);

                                }

                                preferences.setArrayStringPreferences(context, "ArrayItemList", item);


                                ((ListItemFragment) fragmentActivity.getSupportFragmentManager().getFragments().get(0)).refreshAdapter();

                                dismiss();

                            }
                        });


                        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }

                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();


                    }

                    dismiss();

                    return true;
                } else return false;
            }
        });
    }


}
