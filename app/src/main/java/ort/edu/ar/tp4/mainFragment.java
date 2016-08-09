package ort.edu.ar.tp4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mainFragment extends Fragment implements OnMapReadyCallback {

    MainActivity ma = (MainActivity)getActivity();
    String url ="http://ort.edu.ar/serviciox";


    GoogleMap map;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        return v;
    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true); // Habilita +/- para hacer zoom
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);    // Selecciona tipo de mapa satelital

        LatLng latLng = new LatLng(-34.61315,-58.37723);
        CameraUpdate bsas= CameraUpdateFactory.newLatLng(latLng);
        map.moveCamera(bsas); //se movio la camara
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(2);
        map.animateCamera(zoom); //zoom al lugar

        MarkerOptions mo = new MarkerOptions()
                .position(latLng)
                .title(("Buenos Aires"));
        map.addMarker(mo);
    }

    public mainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mainFragment newInstance(String param1, String param2) {
        mainFragment fragment = new mainFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        //    mParam1 = getArguments().getString(ARG_PARAM1);//
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    }


