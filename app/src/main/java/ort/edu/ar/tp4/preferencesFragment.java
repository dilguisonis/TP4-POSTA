package ort.edu.ar.tp4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class preferencesFragment extends Fragment implements View.OnClickListener {

    EditText NumeroET;
    EditText auxNumeroET;
    CheckBox chkBox;
    Button btn;
    int NumeroHabitanteMinimo;
    int DistanciaMaxima;
    boolean EsCapital;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_preferences, container, false);
        chkBox = (CheckBox) v.findViewById(R.id.chkBox);

        btn =(Button) v.findViewById(R.id.BOTON);
        btn.setOnClickListener(this);
                MainActivity ma = (MainActivity)getActivity();
        NumeroET = (EditText) v.findViewById(R.id.numHabitante);
        auxNumeroET = (EditText) v.findViewById(R.id.auxnumHabitante);
        EsCapital = false;
        return v;
    }
    public void onClick(View view) {
    setter();
    }
    public void setter(){
        MainActivity ma = (MainActivity)getActivity();
        NumeroET.setText(String.valueOf(NumeroHabitanteMinimo));
        ma.aux1 = NumeroHabitanteMinimo;
        auxNumeroET.setText(String.valueOf(DistanciaMaxima));
        ma.aux2 = DistanciaMaxima;
        if (chkBox.isChecked())
        {
            EsCapital = true;
        }else {EsCapital = false;}
        ma.aux = EsCapital;
    }

}
