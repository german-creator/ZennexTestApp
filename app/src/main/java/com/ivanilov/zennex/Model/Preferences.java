package com.ivanilov.zennex.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для использования API - интерфейсов Preference
 * @autor Герман Иванилов
 * @version 1.0
 */
public class Preferences {


    public ArrayList<String> getArrayStringPreferences(Context context, String key) {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Gson gson = new Gson();
        final ArrayList<String> result = gson.fromJson(myPreferences.getString(key, null), ArrayList.class);

        return result;

    }


    public void setStringPreferences(Context context, String key, String s) {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor myEditor = myPreferences.edit();


        myEditor.putString(key, s);

        myEditor.commit();

    }

    public void setArrayStringPreferences(Context context, String key, List<String> s) {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        Gson gson = new Gson();


        myEditor.putString(key, gson.toJson(s));

        myEditor.commit();

    }

    public ArrayList<Boolean> getArrayBooleanPreferences(Context context, String key) {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        int size = myPreferences.getInt(key, 0);
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < size; i++)
            result.add(i, myPreferences.getBoolean(key + i, false));


        return result;

    }


    public void setArrayBooleanPreferences(Context context, String key, List<Boolean> array) {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putInt(key, array.size());

        for (int i = 0; i < array.size(); i++) {
            myEditor.putBoolean(key + i, array.get(i));
        }

        myEditor.commit();

    }


}
