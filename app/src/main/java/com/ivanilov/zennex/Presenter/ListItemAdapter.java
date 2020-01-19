package com.ivanilov.zennex.Presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Dialog.DialogListAdd;
import com.ivanilov.zennex.View.Dialog.DialogListDelete;
import com.ivanilov.zennex.View.Fragment.ListItemFragment;

import java.util.List;

public class ListItemAdapter extends BaseAdapter {

    private ListItemFragment view;
    private List<String> item;
    private List<Boolean> itemCheck;


    public ListItemAdapter(ListItemFragment view, List<String> item, List<Boolean> itemCheck) {

        this.view = view;
        this.item = item;
        this.itemCheck = itemCheck;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public String getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(view.getContext()).inflate(R.layout.item_list, parent, false);
        }
        final ImageView imageView = convertView.findViewById(R.id.item_list_image);
        final TextView textView = convertView.findViewById(R.id.item_list_text);
        final CheckBox checkBox = convertView.findViewById(R.id.item_list_check);

        textView.setText(item.get(position));
        checkBox.setChecked(itemCheck.get(position));

        if (checkBox.isChecked()) setImageCheck(imageView);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   setImageCheck(imageView);
                   itemCheck.set(position, true);
                   view.saveItemBoolean(itemCheck);
                } else {
                   setImageUnCheck(imageView);
                   itemCheck.set(position, false);
                   view.saveItemBoolean(itemCheck);

                }
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", item.get(position));
                bundle.putInt("Position", position);
                DialogListAdd dialogListAdd = new DialogListAdd();
                dialogListAdd.setArguments(bundle);
                dialogListAdd.show(view.getFragmentManager(), "");
            }
        });


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", item.get(position));
                bundle.putInt("Position", position);
                DialogListDelete dialogListDelete = new DialogListDelete();
                dialogListDelete.setArguments(bundle);
                dialogListDelete.show(view.getFragmentManager(), "");
                return true;
            }
        });

        textView.setText(item.get(position));

        return convertView;
    }

    public void setImageCheck (ImageView imageView){
        int resID = imageView.getContext().getResources().getIdentifier("ic_broken_image_accent_24dp", "drawable", imageView.getContext().getPackageName());
        imageView.setImageResource(resID);
    }

    public void setImageUnCheck (ImageView imageView){
        int resID = imageView.getContext().getResources().getIdentifier("ic_image_primary_24dp", "drawable", imageView.getContext().getPackageName());
        imageView.setImageResource(resID);
    }

}