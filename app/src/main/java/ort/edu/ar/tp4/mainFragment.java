package ort.edu.ar.tp4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Random;

public class mainFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {



    String url ="http://ort.edu.ar/serviciox";
    ArrayList<Dato> ciudades;
    Random rn = new Random();
    TextView text;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    boolean aux = false;
    int tocadas;
    int ganadas;
    int correctoaux;
    String correcto;
    GoogleMap map;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        b1 = (Button) v.findViewById(R.id.button1);
        b2 = (Button) v.findViewById(R.id.button2);
        b3 = (Button) v.findViewById(R.id.button3);
        b4 = (Button) v.findViewById(R.id.button4);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        MainActivity ma = (MainActivity)getActivity();
        ma.gano = ganadas;
        ma.toco = tocadas;
        return v;
    }
    public void setCiudades(ArrayList<Dato> ciudades) {
        this.ciudades = ciudades;
        if (map != null && ciudades != null)
            setMap();
    }
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true); // Habilita +/- para hacer zoom
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);    // Selecciona tipo de mapa satelital
        if (map != null && ciudades != null)
            setMap();
    }

    public void onClick(View view) {

        MainActivity ma = (MainActivity)getActivity();

        switch (view.getId()) {
            case R.id.button1:
                if (b1.getText().toString() == correcto) {
                    ma.setGano();
                    ma.setToco();
                    aux = true;
                }
                else {
                    ma.setToco();
                    highlight();
                    setMap();
                }
                break;
            case R.id.button2:
                if (b2.getText().toString() == correcto) {
                    ma.setGano();
                    ma.setToco();
                    aux = true;
                }
                else {
                    ma.setToco();
                    highlight();
                    setMap();
                }
                break;
            case R.id.button3:
                if (b3.getText().toString() == correcto) {
                    ma.setGano();
                    ma.setToco();
                    aux = true;
                }
                else {
                    ma.setToco();
                    highlight();
                    setMap();
                }
                break;
            case R.id.button4:
                if (b4.getText().toString() == correcto) {
                    ma.setGano();
                    ma.setToco();
                    aux = true;
                }
                else {
                    ma.setToco();
                    highlight();
                    setMap();
                }
                break;
        }
        if (aux == true)
        {
            setMap();
            aux = false;

        }

            }

    public void setMap() {
        MainActivity ma = (MainActivity)getActivity();
        map.clear();
        for (int i = 0; i < 4; i++) {
            // Log.d("lol",datos.get(i).name.toString());
            int answer = rn.nextInt(ciudades.size());
                if (i == 0)
                    b1.setText(ciudades.get(answer).name.toString());
                if (i == 1)
                    b2.setText(ciudades.get(answer).name.toString());
                if (i == 2)
                    b3.setText(ciudades.get(answer).name.toString());
                if (i == 3)
                    b4.setText(ciudades.get(answer).name.toString());
        }
        int answeraux = rn.nextInt(3);
        int answer = rn.nextInt(ciudades.size());
        switch ( answeraux){
            case 0:
                b1.setText(ciudades.get(answer).name.toString());
                break;
            case 1:
                b2.setText(ciudades.get(answer).name.toString());
                break;
            case 2:
                b3.setText(ciudades.get(answer).name.toString());
                break;
            case 3:
                b4.setText(ciudades.get(answer).name.toString());
                break;
        }
        correcto = ciudades.get(answer).name.toString();
        correctoaux = answeraux;
        CameraUpdate loc = CameraUpdateFactory.newLatLng(ciudades.get(answer).getLatLng());
        map.moveCamera(loc); //se movio la camara
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(2);
        map.animateCamera(zoom); //zoom al lugar
        MarkerOptions mo = new MarkerOptions()
                .position(ciudades.get(answer).getLatLng());
        map.addMarker(mo);
    }

    public mainFragment() {
        // Required empty public constructor
    }
    public void highlight()
    {
        Toast.makeText(getContext(), correcto.toString(), Toast.LENGTH_SHORT).show();
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

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        //    mParam1 = getArguments().getString(ARG_PARAM1);//
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
        for (int i=0; i < 4; i++) {
            // Log.d("lol",datos.get(i).name.toString());
            int answer = rn.nextInt(10) + 1;
            if (i == 0)
            {

                text.setText(this.ciudades.get(answer).name.toString());
            }
            if (i == 1)
            {
                text = (TextView) view.findViewById(R.id.button2);
                text.setText(this.ciudades.get(answer).name.toString());
            }
            if (i == 2)
            {
                text = (TextView) view.findViewById(R.id.button3);
                text.setText(this.ciudades.get(answer).name.toString());
            }
            if (i == 3)
            {
                text = (TextView) view.findViewById(R.id.button4);
                text.setText(this.ciudades.get(answer).name.toString());
            }
        }
        int answer = rn.nextInt(3) + 0;
        LatLng lat = ((LatLng) this.ciudades.get(answer).getLatLng());
        setMap(lat);

    }
    */

}


