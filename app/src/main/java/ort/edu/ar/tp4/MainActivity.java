package ort.edu.ar.tp4;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity  {
GoogleMap map;
ArrayList<Dato> DatosMA = new ArrayList<Dato>();
String url = "http://ort.edu.ar/serviciox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new mainFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenedor,fragment)
                .commit();
        new BuscarDatosTask().execute(url);

    }

    private class BuscarDatosTask extends AsyncTask<String, Void, ArrayList<Dato>>  {
        ArrayList<Dato> ciudades = new ArrayList<>();

        protected void onPostExecute(ArrayList<Dato> datos) {
            super.onPostExecute(datos);

            for (int i=0; i < datos.size(); i++) {
                Log.d("lol",datos.get(i).name.toString());
            }
        }

        @Override
        protected ArrayList<Dato> doInBackground(String... parametros) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://tp4ort.firebaseio.com/geonames.json")
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String result = response.body().string();
                return parsearResultado(result);

            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                return null;
            }



        }
        ArrayList<Dato> parsearResultado(String resultado) {

            Gson gson = new Gson();
            Dato[] arr = gson.fromJson(resultado, Dato[].class);
            return new ArrayList<>(Arrays.asList(arr));}

    }

        }


