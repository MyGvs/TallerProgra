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
                new addMatch().execute();
            }
        });
        /* Agregar los retrieves */
        new retrieveGrupos().execute();
        new retrieveModalidades().execute();
        new retrieveLugar().execute();
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
            String cadena = "select * from GFA_GRUPO order by idgrupo";
            ArrayList<classGrupo> grupos = new ArrayList<classGrupo>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDGRUPO") + " - "+ resultado.getString("nombre_grupo"));
                    classGrupo grupo = new classGrupo(resultado.getInt("IDGRUPO"), resultado.getString("nombre_grupo"));
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
                arraySpinnerGrupoA.add(result.get(i).getIdGrupo()+"."+result.get(i).getGrupo());
                arraySpinnerGrupoB.add(result.get(i).getIdGrupo()+"."+result.get(i).getGrupo());
                arraySpinnerGrupoAr.add(result.get(i).getIdGrupo()+"."+result.get(i).getGrupo());
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
    class retrieveModalidades extends AsyncTask<Void, Void, ArrayList<classModalidad> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<classModalidad> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_modalidad order by idmodalidad";
            ArrayList<classModalidad> modalidades = new ArrayList<classModalidad>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDMODALIDAD") + " - "+ resultado.getString("MODALIDAD"));
                    classModalidad modalidad = new classModalidad(resultado.getInt("IDMODALIDAD"), resultado.getString("MODALIDAD"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    modalidades.add(modalidad);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return modalidades;
        }

        protected void onPostExecute(ArrayList<classModalidad> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdModalidad()+"");
                arraySpinnerModalidad.add(result.get(i).getIdModalidad()+"."+result.get(i).getModalidad());
            }
            ArrayAdapter<String> modalidades = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerModalidad);
            modalidad.setAdapter(modalidades);

        }
    }
    class retrieveLugar extends AsyncTask<Void, Void, ArrayList<classLugar> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<classLugar> doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_LUGARENC order by idlugarencuentro";
            ArrayList<classLugar> lugares = new ArrayList<classLugar>();
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("IDLUGARENCUENTRO") + " - "+ resultado.getString("LUGAR"));
                    classLugar lugar = new classLugar(resultado.getInt("IDLUGARENCUENTRO"), resultado.getString("LUGAR"));
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                    lugares.add(lugar);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "";
            }
            return lugares;
        }

        protected void onPostExecute(ArrayList<classLugar> result) {
            for(int i=0 ; i<result.size(); i++){
                Log.e("Con for", result.get(i).getIdLugar()+"");
                arraySpinnerLugar.add(result.get(i).getIdLugar()+"."+result.get(i).getLugar());
            }
            ArrayAdapter<String> lugares = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, arraySpinnerLugar);
            lugar_e.setAdapter(lugares);

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
            String[] idLugarEncuentroSplit = lugar_e.getSelectedItem().toString().split("\\.");
            String[] idGrupoASplit = grupo_a.getSelectedItem().toString().split("\\.");
            String[] idGrupoBSplit = grupo_b.getSelectedItem().toString().split("\\.");
            String[] idGrupoArSplit = grupo_ar.getSelectedItem().toString().split("\\.");
            String[] idTorneoModSplit = modalidad.getSelectedItem().toString().split("\\.");

            // parametros
            String resultadoA = "-1";
            String resultadoB = "-1";
            String fecha = "11/NOV/2016";
            String idLugarEncuentro = idLugarEncuentroSplit[0].toString();
            String idGrupoA = idGrupoASplit[0].toString();
            String idGrupoB = idGrupoBSplit[0].toString();
            String idGrupoArbitro = idGrupoArSplit[0].toString();
            String idTorneoMod = idTorneoModSplit[0].toString();

            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "insert into GFA_PARTIDO(resultado_a, resultado_b, fecha, idlugarencuentro, idgrupo1, idgrupo2, idarbitros, idtorneomod) VALUES('"+
                    resultadoA+"' ,"+
                    "'"+resultadoB+"' ,"+
                    "TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss') ,"+
                    "'"+idLugarEncuentro+"' ,"+
                    "'"+idGrupoA+"' ,"+
                    "'"+idGrupoB+"' ,"+
                    "'"+idGrupoArbitro+"' ,"+
                    "'"+idTorneoMod+"')";
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
            }else{
                Toast.makeText(getActivity(), "Ocurrio un error.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
