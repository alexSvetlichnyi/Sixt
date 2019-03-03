package com.alex.sixt.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.alex.sixt.R;
import com.alex.sixt.model.Car;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class CarsMapFragment extends Fragment implements OnMapReadyCallback {
    private CarsViewModel carsViewModel;
    private GoogleMap googleMap;

    public static CarsMapFragment newInstance() {
        return new CarsMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carsViewModel = ViewModelProviders.of(getActivity()).get(CarsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        carsViewModel.getUpdateCars().observe(this, this::updateCarsOnMap);
        return view;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int playServiceStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
        if (playServiceStatus == ConnectionResult.SUCCESS) {

            FragmentManager fm = getChildFragmentManager();
            SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
            }

            mapFragment.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap = googleMap;
        updateCarsOnMap(carsViewModel.getCars());
    }

    private void updateCarsOnMap(List<Car> cars) {
        if (googleMap == null) {
            return;
        }
        boolean isFirstItem = true;
        for (Car car : cars) {
            LatLng position = new LatLng(car.getLatitude(), car.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(position)
                    .title(car.getName())
                    .snippet(car.getLicensePlate());

            googleMap.addMarker(markerOptions);

            if (isFirstItem) {
                CameraUpdate updatePosition = CameraUpdateFactory.newLatLngZoom(position, 12);
                googleMap.animateCamera(updatePosition);
                isFirstItem = false;
            }
        }
    }
}