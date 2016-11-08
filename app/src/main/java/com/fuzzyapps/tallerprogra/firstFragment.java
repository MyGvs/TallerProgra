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
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;

import static com.fuzzyapps.tallerprogra.R.id.countryText;
import static com.fuzzyapps.tallerprogra.R.id.createPlayer;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstFragment extends Fragment {
    // Inputs
    private EditText user;
    private EditText password;
    private EditText name;
    private EditText last_name1;
    private EditText last_name2;
    private EditText ci;
    private Spinner country;
    private Spinner genre;
    private Spinner userType;
    private Button registerButton;

    // Instancias de Clases
    private Persona persona;

    // Spinner arrays
    private ArrayList<String> arraySpinnerCountry;
    private ArrayList<String> arraySpinnerGenre;
    private ArrayList<String> arraySpinnerUserType;

    //SQLite Variables
    Connection con;

    public firstFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.first_fragment, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //AQUI INICIALIZAR LOS OBJETOS
        user = (EditText) view.findViewById(R.id.user);
        password = (EditText) view.findViewById(R.id.password);
        name = (EditText) view.findViewById(R.id.name);
        last_name1 = (EditText) view.findViewById(R.id.last_name1);
        last_name2 = (EditText) view.findViewById(R.id.last_name2);
        ci = (EditText) view.findViewById(R.id.ci);
        country = (Spinner) view.findViewById(R.id.country);
        genre = (Spinner) view.findViewById(R.id.genre);
        userType = (Spinner) view.findViewById(R.id.userType);
        registerButton = (Button) view.findViewById(createPlayer);

        // INICIALIZACION DE ARRAYS
        this.arraySpinnerCountry = new ArrayList<String>();
        this.arraySpinnerGenre = new ArrayList<String>();
        this.arraySpinnerUserType = new ArrayList<String>();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("EXITO");
                new addPlayer().execute();
            }
        });

        //new retrieveCountries().execute();
        retrieveCountries();
        //new retrieveGenre().execute();
        retrieveGenre();
        //new retrieveUserType().execute();
        retrieveUserType();
    }
    private void retrieveCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, countries);
        country.setAdapter(countryAdapter);
    }
    private void retrieveGenre() {
        arraySpinnerGenre.add("1. Femenino");
        arraySpinnerGenre.add("2. Masculino");
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerGenre);
        genre.setAdapter(genreAdapter);
    }
    private void retrieveUserType() {
        arraySpinnerUserType.add("1. Jugador");
        arraySpinnerUserType.add("2. Arbitro");
        arraySpinnerUserType.add("3. Administrador");
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerUserType);
        userType.setAdapter(genreAdapter);
    }

    class addPlayer extends AsyncTask<Void, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            // Split de variables
            String[] genreSplit = genre.getSelectedItem().toString().split("\\.");
            String[] userTypeSplit = userType.getSelectedItem().toString().split("\\.");
            String countrySplit = country.getSelectedItem().toString();
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "insert into gfa_persona(nombre, apellido, ci, idgenero, pais, idTipoPersona, usuario, clave" +
                    ") VALUES('"+name.getText().toString()+"' ,"+
                    "'"+last_name1.getText().toString()+" "+last_name2.getText().toString()+"' ,"+
                    ci.getText().toString()+" ,"+
                    genreSplit[0]+" ,"+
                    "'"+countrySplit+"' ,"+
                    userTypeSplit[0]+" ,"+
                    "'"+user.getText().toString()+"' ,"+
                    "'"+password.getText().toString()+"')";
            System.out.println(cadena);
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                st.execute(cadena);

                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return result;
        }

        protected void onPostExecute(String result) {
            if(result.equals("ok")){
                user.setText("");
                password.setText("");
                name.setText("");
                last_name1.setText("");
                last_name2.setText("");
                ci.setText("");
                country.setSelection(0);
                genre.setSelection(0);
                userType.setSelection(0);
                Toast.makeText(getActivity(), "Se registro correctamente.", Toast.LENGTH_SHORT).show();
                System.out.print("Se registro correctamente.");
            }else{
                Toast.makeText(getActivity(), "Ocurrió un problema con el registro.", Toast.LENGTH_SHORT).show();
                System.out.print("Ocurrió un problema con el registro.");
            }
        }
    }
}
