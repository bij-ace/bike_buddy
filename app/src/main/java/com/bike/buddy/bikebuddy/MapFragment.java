package com.bike.buddy.bikebuddy;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bike.buddy.bikebuddy.retrofit.api.BikeBuddyApi;
import com.bike.buddy.bikebuddy.retrofit.model.Network;
import com.bike.buddy.bikebuddy.retrofit.model.Station;
import com.bike.buddy.bikebuddy.retrofit.model.StationNetworkResponse;
import com.bike.buddy.bikebuddy.retrofit.service.BikeBuddyService;
import com.bike.buddy.bikebuddy.util.NetworkUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = MapFragment.class.getSimpleName();
    private GoogleMap mMap;
    private MapView mMapView;
    private CameraPosition mCameraPosition;
    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;


    private Location mLastKnownLocation;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    private static final int DEFAULT_ZOOM = 15;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private OnFragmentInteractionListener mListener;
    private List<Network> bikeplaces;

    private List<Station> stat;


    private BuddyPrefs prefs;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(List<Network> param1) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        //args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        bikeplaces = (List<Network>) b.getSerializable(ARG_PARAM1);
        //stationsList();
        //networks.forEach(n->Log.e("name", n.getName()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Retrieve location and camera position from saved instance state.
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        prefs = new BuddyPrefs(getContext());
        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        // Build the map.
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();    // This is required to get the map to be displayed immediately when the screen is launched

        try {
            MapsInitializer.initialize((getActivity().getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Prompt the user for permission.
        locationUpdate();
        if (prefs != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(prefs.getLat_val(), prefs.getLong_val()), DEFAULT_ZOOM));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

        stationsList();
        setUpMarkersOnMap();
    }

    private void setUpMarkersOnMap() {
        for (int i = 0; i < bikeplaces.size(); i++) {
            LatLng place = new LatLng(bikeplaces.get(i).getLocation().getLatitude(), bikeplaces.get(i).getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(place).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title(bikeplaces.get(i).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        }
    }

    private void locationUpdate() {
        if (mMap == null) {
            return;
        }
        try {
            if (prefs != null) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void stationsList() {
        if (NetworkUtils.isNetworkAvailable(getActivity().getApplicationContext())) {
            Call call;
            BikeBuddyApi api = BikeBuddyService.createService(AppConstants.APPLICATION_BASE_URL);
            call = api.getStations("denver");
            call.enqueue(new Callback<StationNetworkResponse>() {
                @Override
                public void onResponse(Call<StationNetworkResponse> call, Response<StationNetworkResponse> response) {
                    if (response.isSuccessful()) {
                        Log.e("TESTING", String.valueOf(response.body().getNetwork().getNetwork().size()));
                        stat = response.body().getNetwork().getNetwork();
                        for(int i =0; i< stat.size(); i++)
                        {
                            LatLng place = new LatLng(stat.get(i).getLatitude(), stat.get(i).getLongitude());

                            mMap.addMarker(new MarkerOptions().position(place).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title(stat.get(i).getName()));

                            int finalI = i;
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    String toast = "Free bikes : " + stat.get(finalI).getFree_bikes() + "\n" + "Empty Slots : " + stat.get(finalI).getEmpty_slots();
                                    Toast.makeText(getContext(), toast, Toast.LENGTH_LONG ).show();
                                    Intent intent = new Intent(getContext(), StationResultScreenActivity.class);
                                    intent.putExtra("station", (Serializable) stat);
                                    startActivity(intent);
                                    return false;
                                }
                            });
                        }
                        Log.e("STAT", String.valueOf(stat.get(0).getFree_bikes()));
                    }
                }

                @Override
                public void onFailure(Call<StationNetworkResponse> call, Throwable t) {

                    Log.e("FAILEd", t.getMessage());
                }
            });
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
