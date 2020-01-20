package com.ivanilov.zennex.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Fragment.LanguageFragment;
import com.ivanilov.zennex.View.Fragment.ListItemFragment;
import com.ivanilov.zennex.View.Fragment.MapFragment;
import com.ivanilov.zennex.View.Fragment.ParsingFragment;
import com.ivanilov.zennex.View.Fragment.ScalingFragment;

import java.util.Locale;

/**
 * Основное и единственное активити прилоежния, класс содежит NavigationView для перемещения между фрагментами, в layout для этого
 * класса есть контейнер в котором размещаются все фрагменты, каждый из которых отвечает за конкретный экран. Первый фрагмент, который
 * видит пользователь это фрагмент для выбора языка, дальше этот фрагмент замещается выбранные экраном в меню.
 * @autor Герман Иванилов
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ListItemFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener, ParsingFragment.OnFragmentInteractionListener, ScalingFragment.OnFragmentInteractionListener, LanguageFragment.OnFragmentInteractionListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager = getSupportFragmentManager();
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    private SharedPreferences preferences;
    private Locale locale;
    private String lang;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        lang = preferences.getString("lang", "default");
        if (lang.equals("default")) {
            lang = getResources().getConfiguration().locale.getCountry();
        }
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        context = getApplicationContext();
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);


        LanguageFragment languageFragment = new LanguageFragment();
        fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.add(R.id.container, languageFragment);
        fragmentTransaction.commit();


        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageFragment languageFragment = new LanguageFragment();
                fragmentTransaction = fragmentManager
                        .beginTransaction();

                fragmentTransaction.replace(R.id.container, languageFragment);
                fragmentTransaction.commit();

                drawerLayout.closeDrawer(Gravity.LEFT);

            }
        });


        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        fragmentTransaction = fragmentManager
                .beginTransaction();


        if (menuItem.getTitle().equals("List")) {

            ListItemFragment listItemFragment = new ListItemFragment();

            fragmentTransaction.replace(R.id.container, listItemFragment);
            fragmentTransaction.commit();

        }

        if (menuItem.getTitle().equals("Scaling")) {

            ScalingFragment scalingFragment = new ScalingFragment();

            fragmentTransaction.replace(R.id.container, scalingFragment);
            fragmentTransaction.commit();

        }

        if (menuItem.getTitle().equals("Parsing")) {

            ParsingFragment parsingFragment = new ParsingFragment();

            fragmentTransaction.replace(R.id.container, parsingFragment);
            fragmentTransaction.commit();

        }

        if (menuItem.getTitle().equals("Map")) {

            MapFragment mapFragment = new MapFragment();

            fragmentTransaction.replace(R.id.container, mapFragment);
            fragmentTransaction.commit();

        }

        drawerLayout.closeDrawer(Gravity.LEFT);
        return false;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressed backPressedListener = null;
        for (Fragment fragment : fm.getFragments()) {
            if (fragment instanceof OnBackPressed) {
                backPressedListener = (OnBackPressed) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
    }

}
