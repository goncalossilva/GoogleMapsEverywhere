package com.goncalossilva.googlemapseverywhere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupportMapFragment extends Fragment {
    private static final String KEY_MAP_OPTIONS = ":map_options";

    private MapView mMapView;

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(GoogleMapOptions options) {
        SupportMapFragment mapFragment = new SupportMapFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(KEY_MAP_OPTIONS, options);
        mapFragment.setArguments(arguments);
        return mapFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.setClassLoader(SupportMapFragment.class.getClassLoader());
        }

        Bundle args = getArguments();
        GoogleMapOptions options = args != null ? (GoogleMapOptions) args.getParcelable(KEY_MAP_OPTIONS) : null;
        mMapView = new MapView(getActivity(), options);
        mMapView.onCreate(savedInstanceState);

        return mMapView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        mMapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.setClassLoader(SupportMapFragment.class.getClassLoader());
        }

        super.onSaveInstanceState(outState);

        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();

        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        mMapView.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mMapView = null;
    }

    public GoogleMap getMap() {
        return mMapView != null ? mMapView.getMap() : null;
    }
}
