package com.ivanilov.zennex.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ivanilov.zennex.Model.Preferences;
import com.ivanilov.zennex.R;


/**
 * Фрагмент переключения языка, пользователи благодаря этому фрагменту могут изменять язык приложения, отдельно от языка устройства
 * @autor Герман Иванилов
 * @version 1.0
 */

public class LanguageFragment extends Fragment {


    private String lang;
    private SharedPreferences preferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_language, container, false);


    }

    @Override
    public void onResume() {
        super.onResume();

        final Context context = getContext();
        final View view = getView();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        lang = preferences.getString("lang", "default");

        Button buttonEn = view.findViewById(R.id.Language_Button_En);
        Button buttonRu = view.findViewById(R.id.Language_Button_Ru);
        TextView textView = view.findViewById(R.id.Language_TextView);

        if (lang.equals("en")) textView.setText("Current language is English");
        if (lang.equals("ru")) textView.setText("Текущий язык - русский");


        buttonEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!lang.equals("en")) {
                    Preferences preferences = new Preferences();
                    preferences.setStringPreferences(context, "lang", "en");

                    Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

            }
        });

        buttonRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lang.equals("ru")) {

                    Preferences preferences = new Preferences();
                    preferences.setStringPreferences(context, "lang", "ru");

                    Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }
            }
        });

    }

    public interface OnFragmentInteractionListener {
    }

}




