package com.ivanilov.zennex.View;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.ivanilov.zennex.R;
import com.ivanilov.zennex.View.Fragment.ListItemFragment;
import com.ivanilov.zennex.View.Fragment.MapFragment;
import com.ivanilov.zennex.View.Fragment.ParsingFragment;
import com.ivanilov.zennex.View.Fragment.ScalingFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ListItemFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener, ParsingFragment.OnFragmentInteractionListener, ScalingFragment.OnFragmentInteractionListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager = getSupportFragmentManager();
    NavigationView navigationView;
    ListItemFragment fragment;
    LocationManager locationManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

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

        FragmentTransaction fragmentTransaction = fragmentManager
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
        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressed) {
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



    public void getOldFragment() {
        // Восстанавливаем уже созданный фрагмент
        fragment = (ListItemFragment) fragmentManager.findFragmentByTag("fragment");

        if (fragment != null) {

            fragmentManager.beginTransaction().add(R.id.container, fragment, "fragment").commit();


        }
        // Если фрагмент не сохранен, создаем новый экземпляр
        if (fragment == null) {
            fragment = new ListItemFragment();
            fragmentManager.beginTransaction().add(R.id.container, fragment, "fragment").commit();
        }
    }

}
