package com.ivanilov.zennex.View.Fragment;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ivanilov.zennex.R;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;



/**
 * Фрагмент содержащий в себе MapView, исползя Api гугла мы получаем текущее местоположение, находим его на MapView и ниже выводим текущие координаты
 * @autor Герман Иванилов
 * @version 1.0
 */


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    TextView textView;
    private GoogleMap gmap;
    Bundle mapViewBundle;
    LatLng latLng = new LatLng(0.0, 0.0);


    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);


    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

        textView = getView().findViewById(R.id.Fragment_Map_TextView);

        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(getContext());

        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            onMapReady(gmap);
                            textView.setText(latLng.latitude + " " + latLng.longitude);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView = getView().findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(15);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }


    public interface OnFragmentInteractionListener {
    }

}




