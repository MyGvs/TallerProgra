package com.fuzzyapps.tallerprogra;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * A simple {@link Fragment} subclass.
 */
public class thirdFragment extends Fragment {
    // Inputs
    private EditText year;
    private Button crear;

    // Gran bretaña
    private EditText gb_campeon;
    private EditText gb_final;
    private EditText gb_semifinal;
    private EditText gb_cuartos;
    private EditText gb_octavos;
    private EditText gb_clasificatoria;

    // Estados Unidos
    private EditText usa_campeon;
    private EditText usa_final;
    private EditText usa_semifinal;
    private EditText usa_cuartos;
    private EditText usa_octavos;
    private EditText usa_clasificatoria;

    // Francia
    private EditText fr_campeon;
    private EditText fr_final;
    private EditText fr_semifinal;
    private EditText fr_cuartos;
    private EditText fr_octavos;
    private EditText fr_clasificatoria;

    // Australia
    private EditText aus_campeon;
    private EditText aus_final;
    private EditText aus_semifinal;
    private EditText aus_cuartos;
    private EditText aus_octavos;
    private EditText aus_clasificatoria;

    //SQLite Variables
    Connection con;

    public thirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.third_fragment, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializamos los valores de los id torneo

        //AQUI INICIALIZAR LOS OBJETOS
        year = (EditText) view.findViewById(R.id.year);
        crear = (Button) view.findViewById(R.id.createGs);

        // Gran Breataña
        gb_campeon = (EditText) view.findViewById(R.id.gb_campeon);
        gb_final = (EditText) view.findViewById(R.id.gb_final);
        gb_semifinal = (EditText) view.findViewById(R.id.gb_semifinal);
        gb_cuartos = (EditText) view.findViewById(R.id.gb_cuartos);
        gb_octavos = (EditText) view.findViewById(R.id.gb_octavos);
        gb_clasificatoria = (EditText) view.findViewById(R.id.gb_clasificatoria);

        // Estados Unidos
        usa_campeon = (EditText) view.findViewById(R.id.usa_campeon);
        usa_final = (EditText) view.findViewById(R.id.usa_final);
        usa_semifinal = (EditText) view.findViewById(R.id.usa_semifinal);
        usa_cuartos = (EditText) view.findViewById(R.id.usa_cuartos);
        usa_octavos = (EditText) view.findViewById(R.id.usa_octavos);
        usa_clasificatoria = (EditText) view.findViewById(R.id.usa_clasificatoria);

        // Francia
        fr_campeon = (EditText) view.findViewById(R.id.fr_campeon);
        fr_final = (EditText) view.findViewById(R.id.fr_final);
        fr_semifinal = (EditText) view.findViewById(R.id.fr_semifinal);
        fr_cuartos = (EditText) view.findViewById(R.id.fr_cuartos);
        fr_octavos = (EditText) view.findViewById(R.id.fr_octavos);
        fr_clasificatoria = (EditText) view.findViewById(R.id.fr_clasificatoria);

        // Australia
        aus_campeon = (EditText) view.findViewById(R.id.aus_campeon);
        aus_final = (EditText) view.findViewById(R.id.aus_final);
        aus_semifinal = (EditText) view.findViewById(R.id.aus_semifinal);
        aus_cuartos = (EditText) view.findViewById(R.id.aus_cuartos);
        aus_octavos = (EditText) view.findViewById(R.id.aus_octavos);
        aus_clasificatoria = (EditText) view.findViewById(R.id.aus_clasificatoria);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new addTorneos().execute();
                new addGrandSlam().execute();
                new addPremios().execute();
            }
        });
    }
    class addTorneos extends AsyncTask<Void, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            // Split de variables
            //Variable del año en String
            //String anio = year.getText().toString();
            //Toast.makeText(getActivity(), "Torneo creado "+anio, Toast.LENGTH_SHORT).show();
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "insert into gfa_torneo(pais)  SELECT 'Gran Bretaña' FROM dual UNION ALL   SELECT 'Estados Unidos' FROM dual UNION ALL   SELECT 'Francia' FROM dual UNION ALL   SELECT 'Australia' FROM dual";
            //System.out.println(cadena);
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
            //Toast.makeText(getActivity(), "Ocurrió un problema con el registro.", Toast.LENGTH_SHORT).show();
            if(result.equals("ok")){
                Toast.makeText(getActivity(), "Se creó con exito los torneos.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Ocurrió un problema con el registro de torneos.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    class addGrandSlam extends AsyncTask<Void, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            // Split de variables
            //Variable del año en String
            String anio = year.getText().toString();
            //Toast.makeText(getActivity(), "Torneo creado "+anio, Toast.LENGTH_SHORT).show();
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena1 = "select idtorneo, pais from (SELECT * FROM gfa_torneo ORDER BY idtorneo DESC) WHERE ROWNUM <= 4 ORDER BY ROWNUM DESC";
            String cadena2 = "insert into gfa_grandSlam(anio, idtorneo1, idtorneo2, idtorneo3, idtorneo4) VALUES('"+anio+"'";
            //String cadena2 = "insert into gfa_grandSlam(anio, idtorneo1, idtorneo2, idtorneo3, idtorneo4) VALUES('"+anio+"', '1', '2', '3','4')";
            //System.out.println(cadena1);
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena1);
                while(resultado.next()){
                    //Log.e("OK", resultado.getInt("idtorneo") + " - "+ resultado.getString("pais"));
                    cadena2 = cadena2 + ", '"+resultado.getInt("idtorneo")+"'";
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                }
                cadena2 = cadena2 + ")";
                //System.out.println(cadena2);
                st.execute(cadena2);
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
                Toast.makeText(getActivity(), "Se creó correctamente el grand Slam.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Ocurrió un problema con el registro de grand slam.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    class addPremios extends AsyncTask<Void, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            // Gran Bretaña
            String gb_campeon_dato = gb_campeon.getText().toString();
            String gb_final_dato = gb_final.getText().toString();;
            String gb_semifinal_dato = gb_semifinal.getText().toString();;
            String gb_cuartos_dato = gb_cuartos.getText().toString();;
            String gb_octavos_dato = gb_octavos.getText().toString();;
            String gb_clasificatoria_dato = gb_clasificatoria.getText().toString();

            // Estados Unidos
            String usa_campeon_dato = usa_campeon.getText().toString();
            String usa_final_dato = usa_final.getText().toString();;
            String usa_semifinal_dato = usa_semifinal.getText().toString();;
            String usa_cuartos_dato = usa_cuartos.getText().toString();;
            String usa_octavos_dato = usa_octavos.getText().toString();;
            String usa_clasificatoria_dato = usa_clasificatoria.getText().toString();

            // Francia
            String fr_campeon_dato = fr_campeon.getText().toString();
            String fr_final_dato = fr_final.getText().toString();;
            String fr_semifinal_dato = fr_semifinal.getText().toString();;
            String fr_cuartos_dato = fr_cuartos.getText().toString();;
            String fr_octavos_dato = fr_octavos.getText().toString();;
            String fr_clasificatoria_dato = fr_clasificatoria.getText().toString();

            // Australia
            String aus_campeon_dato = aus_campeon.getText().toString();
            String aus_final_dato = aus_final.getText().toString();;
            String aus_semifinal_dato = aus_semifinal.getText().toString();;
            String aus_cuartos_dato = aus_cuartos.getText().toString();;
            String aus_octavos_dato = aus_octavos.getText().toString();;
            String aus_clasificatoria_dato = aus_clasificatoria.getText().toString();

            //Toast.makeText(getActivity(), "Torneo creado "+anio, Toast.LENGTH_SHORT).show();
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena1 = "select * from gfa_grandslam where idgrandSlam = (select max(idgrandslam) as idgrandslam from gfa_grandslam)";
            String cadena2 = "";
            //String cadena2 = "insert into gfa_grandSlam(anio, idtorneo1, idtorneo2, idtorneo3, idtorneo4) VALUES('"+anio+"', '1', '2', '3','4')";
            //System.out.println(cadena1);
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena1);
                if(resultado.next()){
                    //Log.e("OK", resultado.getInt("maxi")+"");
                    cadena2 = "insert into gfa_premio(premio, idtorneo, idfase)  " +
                            "SELECT '"+gb_campeon_dato+"', '"+resultado.getInt("IDTORNEO1")+"','1'  FROM dual UNION ALL   " +
                            "SELECT '"+gb_final_dato+"', '"+resultado.getInt("IDTORNEO1")+"','2'  FROM dual UNION ALL   " +
                            "SELECT '"+gb_semifinal_dato+"', '"+resultado.getInt("IDTORNEO1")+"','3'  FROM dual UNION ALL   " +
                            "SELECT '"+gb_cuartos_dato+"', '"+resultado.getInt("IDTORNEO1")+"','4'  FROM dual UNION ALL   " +
                            "SELECT '"+gb_octavos_dato+"', '"+resultado.getInt("IDTORNEO1")+"','5'  FROM dual UNION ALL   " +
                            "SELECT '"+gb_clasificatoria_dato+"', '"+resultado.getInt("IDTORNEO1")+"','6'  FROM dual UNION ALL   " +
                            "SELECT '"+usa_campeon_dato+"', '"+resultado.getInt("IDTORNEO2")+"','1'  FROM dual UNION ALL   " +
                            "SELECT '"+usa_final_dato+"', '"+resultado.getInt("IDTORNEO2")+"','2'  FROM dual UNION ALL   " +
                            "SELECT '"+usa_semifinal_dato+"', '"+resultado.getInt("IDTORNEO2")+"','3'  FROM dual UNION ALL   " +
                            "SELECT '"+usa_cuartos_dato+"', '"+resultado.getInt("IDTORNEO2")+"','4'  FROM dual UNION ALL   " +
                            "SELECT '"+usa_octavos_dato+"', '"+resultado.getInt("IDTORNEO2")+"','5'  FROM dual UNION ALL   " +
                            "SELECT '"+usa_clasificatoria_dato+"', '"+resultado.getInt("IDTORNEO2")+"','6'  FROM dual UNION ALL   " +
                            "SELECT '"+fr_campeon_dato+"', '"+resultado.getInt("IDTORNEO3")+"','1'  FROM dual UNION ALL   " +
                            "SELECT '"+fr_final_dato+"', '"+resultado.getInt("IDTORNEO3")+"','2'  FROM dual UNION ALL   " +
                            "SELECT '"+fr_semifinal_dato+"', '"+resultado.getInt("IDTORNEO3")+"','3'  FROM dual UNION ALL   " +
                            "SELECT '"+fr_cuartos_dato+"', '"+resultado.getInt("IDTORNEO3")+"','4'  FROM dual UNION ALL   " +
                            "SELECT '"+fr_octavos_dato+"', '"+resultado.getInt("IDTORNEO3")+"','5'  FROM dual UNION ALL   " +
                            "SELECT '"+fr_clasificatoria_dato+"', '"+resultado.getInt("IDTORNEO3")+"','6'  FROM dual UNION ALL   " +
                            "SELECT '"+aus_campeon_dato+"', '"+resultado.getInt("IDTORNEO4")+"','1'  FROM dual UNION ALL   " +
                            "SELECT '"+aus_final_dato+"', '"+resultado.getInt("IDTORNEO4")+"','2'  FROM dual UNION ALL   " +
                            "SELECT '"+aus_semifinal_dato+"', '"+resultado.getInt("IDTORNEO4")+"','3'  FROM dual UNION ALL   " +
                            "SELECT '"+aus_cuartos_dato+"', '"+resultado.getInt("IDTORNEO4")+"','4'  FROM dual UNION ALL   " +
                            "SELECT '"+aus_octavos_dato+"', '"+resultado.getInt("IDTORNEO4")+"','5'  FROM dual UNION ALL   " +
                            "SELECT '"+aus_clasificatoria_dato+"', '"+resultado.getInt("IDTORNEO4")+"','6'  FROM dual";
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                }
                //System.out.println(cadena2);
                st.execute(cadena2);
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
                Toast.makeText(getActivity(), "Se creó correctamente los premios de los torneos.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Ocurrió un problema con el registro de premio.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
