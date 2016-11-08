package com.fuzzyapps.tallerprogra;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.*;
import java.sql.ResultSet;

public class LoginActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;
    private Button loginButton;
    private Button backDoorButton;
    //SQLite Variables
    Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUserView = (EditText) findViewById(R.id.user);
        mPasswordView = (EditText) findViewById(R.id.password);

        loginButton = (Button) findViewById(R.id.signin);
        backDoorButton = (Button) findViewById(R.id.backDoor);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                //Intent i = new Intent(LoginActivity.this, activityNavigation.class);
                //startActivity(i);
            }
        });

        backDoorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, activityNavigation.class);
                startActivity(i);
            }
        });
        // new retrieveData().execute();
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid user, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        new loginUser().execute();
    }
    class retrieveData extends AsyncTask<Void, Void, String> {


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
            String cadena = "select * from s_REGION order by 1";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while(resultado.next()){
                    Log.e("OK", resultado.getString("id") + " - "+ resultado.getString("name"));
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
                //OK

            }
        }
    }
    class loginUser extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            String user = mUserView.getText().toString();
            String pass = mPasswordView.getText().toString();

            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "select * from GFA_PERSONA where usuario='"+user+"' AND clave='"+pass+"'";
            try{
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL,UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                System.out.print(cadena);
                if(resultado.next()){
                    Log.e("OK", resultado.getString("usuario") + " - "+ resultado.getString("clave"));
                    if(resultado.getInt("IDTIPOPERSONA") == 1)result = "usuario";
                    else if(resultado.getInt("IDTIPOPERSONA") == 3)result = "admin";
                    else{
                        result = "nook";
                    }
                }else{
                    result = "nook";
                }
                resultado.close();
                st.close();
                con.close();
            }catch (Exception e){
                Log.e("ERROR", e.toString());
                result = "nook";
            }
            return result;
        }

        protected void onPostExecute(String result) {
            if (result.equals("nook")){
                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();

            }else if(result.equals("usuario")){
                Intent i = new Intent(LoginActivity.this, playerActivity.class);
                startActivity(i);
            }else if(result.equals("admin")){
                Intent i = new Intent(LoginActivity.this, activityNavigation.class);
                startActivity(i);
            }
        }
    }
    /*private void prepararTablaPais() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for(int i=0; i<countries.size();i++){
            new addCountry(countries.get(i), (i+1)).execute();
            //Log.e("asd",countries.get(i));
        }
    }
    class addCountry extends AsyncTask<Void, Void, String> {
        String country;
        int id;
        public addCountry(String country, int id){
            this.country = country;
            this.id = id;
        }

        private Exception exception;
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String cadena = "INSERT INTO GFA_PAIS(IDPAIS,PAIS) VALUES ("+id+", '"+country+"')";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            Log.e("INSERT", id +" - "+ country);
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

        }
    }*/
}

