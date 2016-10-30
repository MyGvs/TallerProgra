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
import java.util.ArrayList;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.*;
import java.util.*;

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
    private SQLite sqlite;
    Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqlite = new SQLite(this);
        sqlite.abrir();
        /*// Agregar Modalidad
        sqlite.addModalidad("Individual Femenino");
        sqlite.addModalidad("Dobles Femenino");
        sqlite.addModalidad("Individual Masculino");
        sqlite.addModalidad("Dobles Masculino");
        sqlite.addModalidad("Dobles Mixtos");
        //EJEMPLO PARA AGREGAR UN PAIS
        sqlite.addPais("Bolivia");
        sqlite.addPais("Chile");
        sqlite.addPais("Bangladesh");
        //Add Jugadores
        sqlite.addUserType("Jugador");
        sqlite.addUserType("Arbitro");
        sqlite.addUserType("Administrador");
        //Add genero
        sqlite.addGenre("Femenino");
        sqlite.addGenre("Masculino");*/
        //EJEMPLO PARA LISTAR LOS PAISES
        try {
            Cursor cursor = sqlite.getAllPais();
            if( cursor.moveToFirst() ) {
                do {
                    // 0: idpais, 1: pais
                    //el numero debe ser correlativo a lo que usieron en el SELECT del query
                    //si es entero usan  cursor.getInt()
                    // si es varchar usan
                    //en este caso el id es el sub 0 y es un int y el pais es un varchar uso el getString()
                    //Toast.makeText(this,cursor.getInt(0)+" - "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        sqlite.cerrar();
        // Set up the login form.
        mUserView = (EditText) findViewById(R.id.user);
        mPasswordView = (EditText) findViewById(R.id.password);

        loginButton = (Button) findViewById(R.id.signin);
        backDoorButton = (Button) findViewById(R.id.backDoor);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                Intent i = new Intent(LoginActivity.this, activityNavigation.class);
                startActivity(i);
            }
        });

        backDoorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, activityNavigation.class);
                startActivity(i);
            }
        });
        new retrieveData().execute();
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid user, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        sqlite.abrir();
        // Store values at the time of the login attempt.
        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();
        Cursor player = sqlite.login(user, password);
        if(player.getCount() > 0){
            //Toast.makeText(this, "Exito",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, activityNavigation.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Usuario o clave incorrectos.",Toast.LENGTH_SHORT).show();
        }
        /*
        if(sqlite.login(user, password)) {
            Toast.makeText(this, "Exito",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fracasado",Toast.LENGTH_SHORT).show();
        }
        */
        //Toast.makeText(this, user+" -- "+password,Toast.LENGTH_SHORT).show();
        sqlite.cerrar();
    }
    class retrieveData extends AsyncTask<Void, Void, String> {

        private Exception exception;
        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String cadena = "select * from s_REGION order by 1";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
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

        }
    }
}

