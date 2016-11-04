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

        new retrieveCountries().execute();
        new retrieveGenre().execute();
        new retrieveUserType().execute();
    }
    class retrieveCountries extends AsyncTask<Void, Void, ArrayList<classPais> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<classPais> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_PAIS order by idpais";
            ArrayList<classPais> paises = new ArrayList<classPais>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDPAIS") + " - "+ resultado.getString("PAIS"));
                    classPais pais = new classPais(resultado.getInt("IDPAIS"), resultado.getString("PAIS"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    paises.add(pais);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return paises;
        }

        protected void onPostExecute(ArrayList<classPais> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdPais()+"");
                arraySpinnerCountry.add(result.get(i).getIdPais()+"."+result.get(i).getPais());
            }
            ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerCountry);
            country.setAdapter(countryAdapter);
        }
    }
    class retrieveGenre extends AsyncTask<Void, Void, ArrayList<classGenero> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<classGenero> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_GENERO order by idGenero";
            ArrayList<classGenero> generos = new ArrayList<classGenero>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDGENERO") + " - "+ resultado.getString("GENERO"));
                    classGenero genero = new classGenero(resultado.getInt("IDGENERO"), resultado.getString("GENERO"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    generos.add(genero);
                }
                System.out.println(generos.size());
                resultado.close();
                st.close();
                con.close();
                result = "ok";
                return generos;
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return null;
        }

        protected void onPostExecute(ArrayList<classGenero> result) {
            for(int i=0 ; i<(int)result.size(); i++){
                Log.e("Con for", result.get(i).getIdGenero()+"");
                arraySpinnerGenre.add(result.get(i).getIdGenero()+"."+result.get(i).getGenero());
            }
            ArrayAdapter<String> genreAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerGenre);
            genre.setAdapter(genreAdapter);
        }
    }
    class retrieveUserType extends AsyncTask<Void, Void, ArrayList<classUserType> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<classUserType> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_T_PERSONA order by idtipopersona";
            ArrayList<classUserType> tipos = new ArrayList<classUserType>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDTIPOPERSONA") + " - "+ resultado.getString("TIPO"));
                    classUserType tipo = new classUserType(resultado.getInt("IDTIPOPERSONA"), resultado.getString("TIPO"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    tipos.add(tipo);
                }
                System.out.println(tipos.size());
                resultado.close();
                st.close();
                con.close();
                result = "ok";
                return tipos;
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return null;
        }

        protected void onPostExecute(ArrayList<classUserType> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdTipoPersona()+"");
                arraySpinnerUserType.add(result.get(i).getIdTipoPersona()+"."+result.get(i).getTipoPersona());
            }
            ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerUserType);
            userType.setAdapter(userTypeAdapter);
        }
    }
    class addPlayer extends AsyncTask<Void, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            // Split de variables
            String[] countrySplit = country.getSelectedItem().toString().split("\\.");
            String[] genreSplit = genre.getSelectedItem().toString().split("\\.");
            String[] userTypeSplit = userType.getSelectedItem().toString().split("\\.");
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "insert into gfa_persona(nombre, apellido, ci, idgenero, idpais, idTipoPersona, usuario, clave" +
                    ") VALUES('"+name.getText().toString()+"' ,"+
                    "'"+last_name1.getText().toString()+" "+last_name2.getText().toString()+"' ,"+
                    ci.getText().toString()+" ,"+
                    genreSplit[0]+" ,"+
                    countrySplit[0]+" ,"+
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
