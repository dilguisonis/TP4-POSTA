package ort.edu.ar.tp4;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by 41823413 on 9/8/2016.
 */
public class Dato {

    String name;
    int population;
    double lat, lng;

    Dato(String nombre,int poblacion, double lat, double lng) {
        this.name = nombre;
        this.population = poblacion;
        this.lat=lat;
        this.lng=lng;
    }

    public LatLng getLatLng(){
        return new LatLng(lat,lng);
    }
}
