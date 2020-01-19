package com.ivanilov.zennex.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.ivanilov.zennex.Presenter.ParsingItem;
import com.ivanilov.zennex.Presenter.ParsingItemAdapter;
import com.ivanilov.zennex.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class ParsingFragment extends Fragment {

    ParsingFragment view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parsing, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        view = this;

        ProgressBar progressBar =  getView().findViewById(R.id.Fragment_Parsing_Progress);
        final ListView listView = getView().findViewById(R.id.Fragment_Parsing_List_View);

        OkHttpClient client = new OkHttpClient();

        String url = "http://quotes.zennex.ru/api/v3/bash/quotes?sort=time";

        final Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    ArrayList<ParsingItem> items = new ArrayList<>();
                    try {
                        items = parseResponse(myResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final ArrayList<ParsingItem> finalItems = items;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ParsingItemAdapter parsingItemAdapter = new ParsingItemAdapter(view, finalItems);
                            listView.setAdapter(parsingItemAdapter);

                        }
                    });
                }


            }
        });


    }

    public interface OnFragmentInteractionListener {
    }


    public ArrayList<ParsingItem> parseResponse(String myResponse) throws JSONException {
        JSONObject reader = new JSONObject(myResponse);
        JSONArray quotes = reader.getJSONArray("quotes");


        ArrayList<ParsingItem> items = new ArrayList<>();

        for (int i = 0; i < quotes.length(); i++) {
            JSONObject object = quotes.getJSONObject(i);

            ParsingItem parsingItem = new ParsingItem(
                    (Integer) object.get("id"),
                    (String) object.get("description"),
                    (String) object.get("time"),
                    (Integer) object.get("rating")

            );

            items.add(parsingItem);

        }

        return items;

    }


}