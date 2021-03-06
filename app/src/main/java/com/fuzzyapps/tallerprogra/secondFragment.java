package com.fuzzyapps.tallerprogra;

import android.app.Fragment;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class secondFragment extends Fragment {
    private ArrayList<String> arrayFemenino = new ArrayList<String>();
    private ArrayList<String> arrayMasculino = new ArrayList<String>();
    private ArrayList<String> arrayMixto = new ArrayList<String>();
    private Button individualF, doblesF, individualM, doblesM, doblesMix;
    private LayoutInflater layoutInflater;
    private ArrayAdapter<String> adapterFemenino, adapterFemenino2, adapterMasculino, adapterMasculino2, adapterMixto, adapterMixto2;
    private Spinner country;
    Connection con;

    public secondFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.second_fragment, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //AQUI INICIALIZAR LOS OBJETOS
        layoutInflater = getActivity().getLayoutInflater();
        individualF = (Button) view.findViewById(R.id.individualF);
        doblesF = (Button) view.findViewById(R.id.doblesF);
        individualM = (Button) view.findViewById(R.id.individualM);
        doblesM = (Button) view.findViewById(R.id.doblesM);
        doblesMix = (Button) view.findViewById(R.id.doblesMix);

        individualF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desplegarDialogoIndividualFemenino();
            }
        });
        doblesF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desplegarDialogoDoblesFemenino();
            }
        });
        individualM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desplegarDialogoIndividualMasculino();
            }
        });
        doblesM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desplegarDialogoDoblesMasculino();
            }
        });
        doblesMix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desplegarDialogoDoblesMixto();
            }
        });
    }

    private void desplegarDialogoIndividualFemenino() {
        arrayFemenino.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO INDIVIDUAL FEMENINO");
        View view = layoutInflater.inflate(R.layout.item_registro_individual, null);
        Button register = (Button) view.findViewById(R.id.register);
        final Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        country = (Spinner) view.findViewById(R.id.country);retrieveCountries();
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        new getAllFemenino().execute();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    String playerID[] = integranteA.getSelectedItem().toString().split("\\.");
                    new registerTeam(Integer.parseInt(playerID[0]), teamName.getText().toString(), 1, 1, country.getSelectedItem().toString()).execute();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoDoblesFemenino() {
        arrayFemenino.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES FEMENINO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        final Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        final Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        country = (Spinner) view.findViewById(R.id.country);retrieveCountries();
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        adapterFemenino2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteB.setAdapter(adapterFemenino2);
        new getAllFemenino().execute();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    String playerID1[] = integranteA.getSelectedItem().toString().split("\\.");
                    String playerID2[] = integranteB.getSelectedItem().toString().split("\\.");
                    new registerTeam(Integer.parseInt(playerID1[0]), Integer.parseInt(playerID2[0]), teamName.getText().toString(), 2, 3, country.getSelectedItem().toString()).execute();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoIndividualMasculino() {
        arrayMasculino.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO INDIVIDUAL MASCULINO");
        View view = layoutInflater.inflate(R.layout.item_registro_individual, null);
        Button register = (Button) view.findViewById(R.id.register);
        final Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        country = (Spinner) view.findViewById(R.id.country);retrieveCountries();
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteA.setAdapter(adapterMasculino);
        new getAllMasculino().execute();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    String playerID[] = integranteA.getSelectedItem().toString().split("\\.");
                    new registerTeam(Integer.parseInt(playerID[0]), teamName.getText().toString(), 1, 2, country.getSelectedItem().toString()).execute();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoDoblesMasculino() {
        arrayMasculino.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES MACULINO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        final Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        final Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        country = (Spinner) view.findViewById(R.id.country);retrieveCountries();
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteA.setAdapter(adapterMasculino);
        adapterMasculino2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteB.setAdapter(adapterMasculino2);
        new getAllMasculino().execute();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    String playerID1[] = integranteA.getSelectedItem().toString().split("\\.");
                    String playerID2[] = integranteB.getSelectedItem().toString().split("\\.");
                    new registerTeam(Integer.parseInt(playerID1[0]), Integer.parseInt(playerID2[0]), teamName.getText().toString(), 2, 4, country.getSelectedItem().toString()).execute();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoDoblesMixto() {
        arrayMixto.clear();arrayFemenino.clear();arrayMasculino.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES MIXTO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        final Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        final Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        country = (Spinner) view.findViewById(R.id.country);retrieveCountries();

        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteA.setAdapter(adapterMasculino);
        adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteB.setAdapter(adapterFemenino);
        new getAllMasculino().execute();
        new getAllFemenino().execute();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    String playerID1[] = integranteA.getSelectedItem().toString().split("\\.");
                    String playerID2[] = integranteB.getSelectedItem().toString().split("\\.");
                    new registerTeam(Integer.parseInt(playerID1[0]), Integer.parseInt(playerID2[0]), teamName.getText().toString(), 2, 5, country.getSelectedItem().toString()).execute();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void notifyChanged(){
        try {
            adapterMasculino.notifyDataSetChanged();
        }catch (Exception e){}
        try {
            adapterMasculino2.notifyDataSetChanged();
        }catch (Exception e){}
        try{
            adapterFemenino.notifyDataSetChanged();
        }catch (Exception e){}
        try{
            adapterFemenino2.notifyDataSetChanged();
        }catch (Exception e){}
        try {
            adapterMixto.notifyDataSetChanged();
        }catch (Exception e){}
        try {
            adapterMixto2.notifyDataSetChanged();
        }catch (Exception e){}
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
    //APIS
    class getAllMasculino extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "SELECT * FROM gfa_persona a, gfa_t_persona b, gfa_genero c " +
                    "WHERE a.idgenero = c.idgenero AND a.idtipoPersona = b.idtipoPersona AND a.idgenero=2 AND a.idtipoPersona=1";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    arrayMasculino.add(resultado.getString("idpersona")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
                    Log.e("got ",""+resultado.getString("idpersona")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
                }
                resultado.close();
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
            if (!result.equals("")){
                notifyChanged();
            }
        }
    }
    class getAllFemenino extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "SELECT * FROM gfa_persona a, gfa_t_persona b, gfa_genero c " +
                    "WHERE a.idgenero = c.idgenero AND a.idtipoPersona = b.idtipoPersona AND a.idgenero=1 AND a.idtipoPersona=1";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    arrayFemenino.add(resultado.getString("idpersona")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
                }
                resultado.close();
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
            if (!result.equals("")){
                notifyChanged();
            }
        }
    }
    class getAllJugador extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            //masculino
            String cadena = "SELECT * FROM gfa_persona a, gfa_t_persona b, gfa_genero c " +
                    "WHERE a.idgenero = c.idgenero AND a.idtipoPersona = b.idtipoPersona AND a.idgenero=2 AND a.idtipoPersona=1";
            //femenino
            String cadena2 = "SELECT * FROM gfa_persona a, gfa_t_persona b, gfa_genero c " +
                    "WHERE a.idgenero = c.idgenero AND a.idtipoPersona = b.idtipoPersona AND a.idgenero=1 AND a.idtipoPersona=1";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    arrayMasculino.add(resultado.getString("idpersona")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
                }
                ResultSet resultado2 = st.executeQuery(cadena2);
                while(resultado2.next()){
                    arrayFemenino.add(resultado.getString("idpersona")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
                }
                resultado.close();
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
            if (!result.equals("")){
                notifyChanged();
            }
        }
    }
    class registerTeam extends AsyncTask<Void, Void, String> {
        public int idjugador1;
        public int idjugador2;
        public String grupo;
        public String pais;
        public int modalidad;
        public int tipo; // tipo 1: un jugador// tipo 2: 2 jugadres
        //tipo 1
        public registerTeam(int idjugador1, String grupo, int tipo, int modalidad, String pais){
            this.idjugador1 = idjugador1;
            this.grupo = grupo;
            this.tipo = tipo;
            this.modalidad = modalidad;
            this.pais = pais;
        }
        //tipo 2
        public registerTeam(int idjugador1, int idjugador2, String grupo, int tipo, int modalidad, String pais){
            this.idjugador1 = idjugador1;
            this.idjugador2 = idjugador2;
            this.grupo = grupo;
            this.tipo = tipo;
            this.modalidad = modalidad;
            this.pais = pais;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String queryTeam = "INSERT INTO GFA_GRUPO(nombre,pais,idmodalidad) VALUES ('"+grupo+"', '"+pais+"', "+modalidad+")";
            String queryPlayer1 = "";
            String queryPlayer2 = "";
            switch (tipo){
                case 1:
                    try{
                        queryPlayer1 = "INSERT INTO GFA_PERGRUPO(idpersona,idgrupo) VALUES ('"+idjugador1+"', (SELECT idgrupo FROM GFA_GRUPO WHERE nombre='"+grupo+"'))";
                        Class.forName(driver).newInstance();
                        con = DriverManager.getConnection(sourceURL,UserName, Password);
                        Statement st = con.createStatement();
                        st.executeQuery(queryTeam);
                        st.executeQuery(queryPlayer1);
                        st.close();
                        con.close();
                        result = "ok";
                    }catch (Exception e){
                        Log.e("ERROR", e.toString());
                        result = "";
                    }
                    break;
                case 2:
                    try{
                        queryPlayer1 = "INSERT INTO GFA_PERGRUPO(idpersona,idgrupo) VALUES ('"+idjugador1+"', (SELECT idgrupo FROM GFA_GRUPO WHERE nombre='"+grupo+"'))";
                        queryPlayer2 = "INSERT INTO GFA_PERGRUPO(idpersona,idgrupo) VALUES ('"+idjugador2+"', (SELECT idgrupo FROM GFA_GRUPO WHERE nombre='"+grupo+"'))";
                        Class.forName(driver).newInstance();
                        con = DriverManager.getConnection(sourceURL,UserName, Password);
                        Statement st = con.createStatement();
                        st.executeQuery(queryTeam);
                        st.executeQuery(queryPlayer1);
                        st.executeQuery(queryPlayer2);
                        st.close();
                        con.close();
                        result = "ok";
                    }catch (Exception e){
                        Log.e("ERROR", e.toString());
                        result = "";
                    }
                    break;
            }
            return result;
        }

        protected void onPostExecute(String result) {
            if (!result.equals("")){
                notificarRegistro();
            }
        }
    }

    private void notificarRegistro() {
        Toast.makeText(getActivity(), "Grupo Registrado!",Toast.LENGTH_SHORT).show();
    }
}
