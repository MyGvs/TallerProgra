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
    private EditText lugar_e;
    private Spinner grupo_ar;
    private Spinner torneo;
    private Spinner fase;
    private Button registerButton;

    // Instancias de Clases
    private classGrupo grupo1;
    private classModalidad modalidad1;

    // Spinner arrays
    private ArrayList<String> arraySpinnerGrupoA;
    private ArrayList<String> arraySpinnerGrupoB;
    private ArrayList<String> arraySpinnerGrupoAr;
    private ArrayList<String> arraySpinnerTorneo;
    private ArrayList<String> arraySpinnerFase;

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
        lugar_e = (EditText) view.findViewById(R.id.place);
        grupo_ar = (Spinner) view.findViewById(R.id.teamR);
        torneo = (Spinner) view.findViewById(R.id.torneo);
        fase = (Spinner) view.findViewById(R.id.fase);
        registerButton = (Button) view.findViewById(R.id.createMatch);

        // INICIALIZACION DE ARRAYS
        this.arraySpinnerGrupoA = new ArrayList<String>();
        this.arraySpinnerGrupoB = new ArrayList<String>();
        this.arraySpinnerGrupoAr = new ArrayList<String>();
        this.arraySpinnerTorneo = new ArrayList<String>();
        this.arraySpinnerFase = new ArrayList<String>();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("EXITO");
                /* Agregar la clase addMatch */
                new addMatch().execute();
            }
        });
        /* Agregar los retrieves */
        new retrieveGrupos().execute();
        new retrieveTorneos().execute();
        new retrieveFases().execute();
        /*
        */
    }

    class retrieveGrupos extends AsyncTask<Void, Void, ArrayList<classGrupo> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<classGrupo> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select distinct a.idgrupo, a.nombre, a.pais, d.tipo " +
                    "from gfa_grupo a, gfa_pergrupo b, gfa_persona c, gfa_t_persona d " +
                    "where a.idgrupo = b.idgrupo " +
                    "and b.idpersona = c.idpersona " +
                    "and c.idtipopersona = d.idtipopersona " +
                    "order by a.idgrupo";
            ArrayList<classGrupo> grupos = new ArrayList<classGrupo>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDGRUPO") + " - "+ resultado.getString("nombre"));
                    classGrupo grupo = new classGrupo(resultado.getInt("IDGRUPO"), resultado.getString("nombre"), resultado.getString("Pais"), resultado.getString("Tipo"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    grupos.add(grupo);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return grupos;
        }

        protected void onPostExecute(ArrayList<classGrupo> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdGrupo()+"");
                if(result.get(i).getTipo().equals("Jugador")){
                    arraySpinnerGrupoA.add(result.get(i).getIdGrupo()+"."+result.get(i).getGrupo());
                    arraySpinnerGrupoB.add(result.get(i).getIdGrupo()+"."+result.get(i).getGrupo());
                }
                if(result.get(i).getTipo().equals("Arbitro")){
                    arraySpinnerGrupoAr.add(result.get(i).getIdGrupo()+"."+result.get(i).getGrupo());
                }
            }
            ArrayAdapter<String> teamAAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerGrupoA);
            grupo_a.setAdapter(teamAAdapter);

            ArrayAdapter<String> teamBAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerGrupoB);
            grupo_b.setAdapter(teamBAdapter);

            ArrayAdapter<String> teamArbAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerGrupoAr);
            grupo_ar.setAdapter(teamArbAdapter);
        }
    }

    class retrieveTorneos extends AsyncTask<Void, Void, ArrayList<classTorneo> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<classTorneo> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_TORNEO order by idtorneo";
            ArrayList<classTorneo> torneos = new ArrayList<classTorneo>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDTORNEO") + " - "+ resultado.getString("PAIS"));
                    classTorneo torneo = new classTorneo(resultado.getInt("IDTORNEO"), resultado.getString("PAIS"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    torneos.add(torneo);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return torneos;
        }

        protected void onPostExecute(ArrayList<classTorneo> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdTorneo()+"");
                arraySpinnerTorneo.add(result.get(i).getIdTorneo()+"."+result.get(i).getPais());
            }
            ArrayAdapter<String> torneos = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerTorneo);
            torneo.setAdapter(torneos);

        }
    }
    class retrieveFases extends AsyncTask<Void, Void, ArrayList<classFase> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<classFase> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_FASE order by idfase";
            ArrayList<classFase> fases = new ArrayList<classFase>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDFASE") + " - "+ resultado.getString("FASE"));
                    classFase fase = new classFase(resultado.getInt("IDFASE"), resultado.getString("FASE"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    fases.add(fase);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return fases;
        }

        protected void onPostExecute(ArrayList<classFase> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdFase()+"");
                arraySpinnerFase.add(result.get(i).getIdFase()+"."+result.get(i).getFase());
            }
            ArrayAdapter<String> fases = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerFase);
            fase.setAdapter(fases);
        }
    }
    class addMatch extends AsyncTask<Void, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            //Splits
            String[] idGrupoASplit = grupo_a.getSelectedItem().toString().split("\\.");
            String[] idGrupoBSplit = grupo_b.getSelectedItem().toString().split("\\.");
            String[] idGrupoArSplit = grupo_ar.getSelectedItem().toString().split("\\.");
            String[] idTorneoSplit = torneo.getSelectedItem().toString().split("\\.");
            String[] idFaseSplit = fase.getSelectedItem().toString().split("\\.");

            // parametros
            String resultadoA = "-1";
            String resultadoB = "-1";
            int day = fecha.getDayOfMonth();
            int month = fecha.getMonth() + 1;
            int year = fecha.getYear();
            String dateMatch =day+"/"+month+"/"+year;
            String idLugarEncuentro = lugar_e.getText().toString();
            String idGrupoA = idGrupoASplit[0].toString();
            String idGrupoB = idGrupoBSplit[0].toString();
            String idGrupoArbitro = idGrupoArSplit[0].toString();
            String idTorneo = idTorneoSplit[0].toString();
            String idFase = idFaseSplit[0].toString();

            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "insert into GFA_PARTIDO(resultado_a, resultado_b, fecha, lugar, idgrupoa, idgrupob, idgrupoar, idtorneo, idfase) VALUES('"+
                    resultadoA+"' ,"+
                    "'"+resultadoB+"' ,"+
                    "to_date('"+dateMatch+"','dd/mm/yyyy') ,"+
                    "'"+idLugarEncuentro+"' ,"+
                    "'"+idGrupoA+"' ,"+
                    "'"+idGrupoB+"' ,"+
                    "'"+idGrupoArbitro+"' ,"+
                    "'"+idTorneo+"' ,"+
                    "'"+idFase+"')";
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
                result = "nook";
            }
            return result;
        }

        protected void onPostExecute(String result) {
            if(result.equals("ok")){
                Toast.makeText(getActivity(), "Se registro correctamente.", Toast.LENGTH_SHORT).show();
                grupo_a.setSelection(0);
                grupo_b.setSelection(0);
                grupo_ar.setSelection(0);
                lugar_e.setText("");
                fase.setSelection(0);
                torneo.setSelection(0);
            }else{
                Toast.makeText(getActivity(), "Ocurrio un error.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
