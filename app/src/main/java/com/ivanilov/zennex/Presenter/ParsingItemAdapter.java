package com.ivanilov.zennex.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Fragment.ParsingFragment;

import java.util.ArrayList;


/**
 * Кастомный адаптер для заполнения элементов ListView в ParsingFragment
 * @autor Герман Иванилов
 * @version 1.0
 */

public class ParsingItemAdapter extends BaseAdapter {

    private ParsingFragment view;
    private ArrayList<ParsingItem> item;


    public ParsingItemAdapter(ParsingFragment view, ArrayList<ParsingItem> item) {

        this.view = view;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public ParsingItem getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(view.getContext()).inflate(R.layout.item_parsing, parent, false);
        }
        final TextView textViewId = convertView.findViewById(R.id.item_parsing_id);
        final TextView textViewRating = convertView.findViewById(R.id.item_parsing_rating);
        final TextView textViewTime = convertView.findViewById(R.id.item_parsing_time);
        final TextView textViewText = convertView.findViewById(R.id.item_parsing_text);

        textViewId.setText(item.get(position).id.toString());
        textViewRating.setText(item.get(position).rating.toString());
        textViewTime.setText(item.get(position).time);
        textViewText.setText(item.get(position).description);


        return convertView;
    }


}