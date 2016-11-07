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

    //SQLite Variables
    Connection con;

    // Ids Torneos
    private int torneo1;
    private int torneo2;
    private int torneo3;
    private int torneo4;
    private int torneoAux;

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
        torneo1 = -1;
        torneo2 = -1;
        torneo3 = -1;
        torneo4 = -1;
        torneoAux = -1;

        //AQUI INICIALIZAR LOS OBJETOS
        year = (EditText) view.findViewById(R.id.year);
        crear = (Button) view.findViewById(R.id.createGs);


        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new addTorneos().execute();
                new addGrandSlam().execute();
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
            String cadena = "insert into gfa_torneo(pais)  SELECT 'Gran Bretaña' FROM dual UNION ALL   SELECT 'Estados Unidos' FROM dual UNION ALL   SELECT 'Francia' FROM dual UNION ALL   SELECT 'Australia' FROM dual" +
                    "";
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
            //Toast.makeText(getActivity(), "Ocurrió un problema con el registro.", Toast.LENGTH_SHORT).show();

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
            System.out.println(cadena1);
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena1);
                while(resultado.next()){
                    Log.e("OK", resultado.getInt("idtorneo") + " - "+ resultado.getString("pais"));
                    cadena2 = cadena2 + ", '"+resultado.getInt("idtorneo")+"'";
                    //this.arraySpinnerCountry.add(resultado.getString("IDPAIS")+"."+resultado.getString("PAIS"));
                }
                cadena2 = cadena2 + ")";
                System.out.println(cadena2);
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
                Toast.makeText(getActivity(), "Se creó corectamente el grand Slam.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Ocurrió un problema con el registro.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
