package com.fuzzyapps.tallerprogra;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A simple {@link Fragment} subclass.
 */
public class fourthFragment extends Fragment {

    private Spinner grupo_a;
    private Spinner grupo_b;
    private DatePicker fecha;
    private Spinner lugar_e;
    private Spinner grupo_ar;
    private Spinner modalidad;
    private Button registerButton;

    // Instancias de Clases
    private classGrupo grupo1;
    private classModalidad modalidad1;

    // Spinner arrays
    private ArrayList<String> arraySpinnerGrupoA;
    private ArrayList<String> arraySpinnerGrupoB;
    private ArrayList<String> arraySpinnerLugar;
    private ArrayList<String> arraySpinnerGrupoAr;
    private ArrayList<String> arraySpinnerModalidad;

    //SQLite Variables
    Connection con;

    public fourthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fourth_fragment, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //AQUI INICIALIZAR LOS OBJETOS
        grupo_a = (Spinner) view.findViewById(R.id.teamA);
        grupo_b = (Spinner) view.findViewById(R.id.teamB);
        fecha = (DatePicker) view.findViewById(R.id.date);
        lugar_e = (Spinner) view.findViewById(R.id.place);
        grupo_ar = (Spinner) view.findViewById(R.id.teamR);
        modalidad = (Spinner) view.findViewById(R.id.modality);
        registerButton = (Button) view.findViewById(R.id.createMatch);

        // INICIALIZACION DE ARRAYS
        this.arraySpinnerGrupoA = new ArrayList<String>();
        this.arraySpinnerGrupoB = new ArrayList<String>();
        this.arraySpinnerLugar = new ArrayList<String>();
        this.arraySpinnerGrupoAr = new ArrayList<String>();
        this.arraySpinnerModalidad = new ArrayList<String>();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("EXITO");
            /* Agregar la clase addMatch */
            /*
                new addMatch().execute();
            */
            }
        });
        /* Agregar los retrieves */
        /*
        new retrieveGrupoA().execute();
        new retrieveGrupoB().execute();
        new retrieveLugar().execute();
        new retrieveGrupoAr().execute();
        new retrieveModalidad().execute();
        */
    }


}
