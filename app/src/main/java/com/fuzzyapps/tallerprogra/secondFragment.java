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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO INDIVIDUAL FEMENINO");
        View view = layoutInflater.inflate(R.layout.item_registro_individual, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        arrayFemenino.clear();
        new getAllFemenino().execute();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){

                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoDoblesFemenino() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES FEMENINO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteB.setAdapter(adapterMasculino);

        //cargarArrayFemenino();
        //cargarArrayMasculino();
        adapterFemenino.notifyDataSetChanged();
        adapterMasculino.notifyDataSetChanged();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){

                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoIndividualMasculino() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO INDIVIDUAL MASCULINO");
        View view = layoutInflater.inflate(R.layout.item_registro_individual, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
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
                    String playerID[] = teamName.getText().toString().split("\\.");


                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoDoblesMasculino() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES MACULINO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteB.setAdapter(adapterMasculino);

        new getAllMasculino().execute();
        //cargarArrayMasculino();
        adapterFemenino.notifyDataSetChanged();
        adapterMasculino.notifyDataSetChanged();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){

                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un nombre de equipo.",Toast.LENGTH_SHORT).show();
                }
                alert.cancel();
            }
        });
        alert.show();
    }
    private void desplegarDialogoDoblesMixto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES MIXTO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
        ArrayAdapter<String> adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        ArrayAdapter<String> adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteB.setAdapter(adapterMasculino);

        //cargarArrayFemenino();
        //cargarArrayMasculino();
        adapterFemenino.notifyDataSetChanged();
        adapterMasculino.notifyDataSetChanged();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){

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
    //APIS
    class getAllMasculino extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayMasculino.clear();
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "SELECT * FROM gfa_persona a, gfa_t_persona b, gfa_genero c " +
                    "WHERE a.idgenero = c.idgenero AND a.idTipoPersona = b.idTipoPersona AND a.idgenero=2 AND a.idTipoPersona=1";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    arrayMasculino.add(resultado.getString("idjugador")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
                    Log.e("got ",""+resultado.getString("idjugador")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
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
                Log.e("got ","listo"+arrayMasculino.size());
                for(int i=0; i<arrayMasculino.size(); i++){
                    Log.e("got ",arrayMasculino.get(i));
                }
            }
        }
    }
    class getAllFemenino extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayFemenino.clear();
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "SELECT * FROM gfa_persona a, gfa_t_persona b, gfa_genero c " +
                    "WHERE a.idgenero = c.idgenero AND a.idTipoPersona = b.idTipoPersona AND a.idgenero=1 AND a.idTipoPersona=1";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    arrayFemenino.add(resultado.getString("idjugador")+". "+resultado.getString("nombre")+" "+resultado.getString("apellido"));
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
}
