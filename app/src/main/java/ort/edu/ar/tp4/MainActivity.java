package ort.edu.ar.tp4;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
GoogleMap map;
ArrayList<Dato> DatosMA = new ArrayList<Dato>();
String url = "http://ort.edu.ar/serviciox";
    mainFragment mapFragment;
    auxFragment resultFragment;
    preferencesFragment configPreference;
    Button b1;
    Button b2;
    Button b3;
    FragmentManager fm;
    int gano = 0;
    int toco = 0;

    int aux1;
    int aux2;
    boolean aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.view1);
        b2 = (Button) findViewById(R.id.view2);
        b3 = (Button) findViewById(R.id.view3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        mapFragment = new mainFragment();
        resultFragment = new auxFragment();
        configPreference = new preferencesFragment();

         fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenedor,mapFragment)
                .commit();
        new BuscarDatosTask().execute(url);

    }

    private class BuscarDatosTask extends AsyncTask<String, Void, ArrayList<Dato>>  {
        ArrayList<Dato> ciudades = new ArrayList<>();

        protected void onPostExecute(ArrayList<Dato> datos) {
            super.onPostExecute(datos);
            mapFragment.setCiudades(datos);

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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.view1:

                fm.beginTransaction()
                        .replace(R.id.contenedor,mapFragment)
                        .commit();
                break;
            case R.id.view2:

                fm.beginTransaction()
                        .replace(R.id.contenedor,resultFragment)
                //contador de click y corrects
                        .commit();
                break;
            case R.id.view3:

                fm.beginTransaction()
                        .replace(R.id.contenedor,configPreference)
                        //contador de click y corrects
                        .commit();
                break;
        }
    }

    public void setGano()
    {
        this.gano += 1;
    }
    public void setToco()
    {
        this.toco += 1;
    }
    public int getGano()
    {
        return gano;
    }
    public int getToco()
    {
        return toco;
    }
    }




