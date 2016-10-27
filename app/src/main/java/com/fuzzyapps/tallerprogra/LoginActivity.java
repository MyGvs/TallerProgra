package com.fuzzyapps.tallerprogra;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;
    private Button loginButton;
    //SQLite Variables
    private SQLite sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqlite = new SQLite(this);
        sqlite.abrir();
        /*
        * Agregar Modalidad
        * */
        /*sqlite.addModalidad("Individual Femenino");
        sqlite.addModalidad("Dobles Femenino");
        sqlite.addModalidad("Individual Masculino");
        sqlite.addModalidad("Dobles Masculino");
        sqlite.addModalidad("Dobles Mixtos");*/
        //EJEMPLO PARA AGREGAR UN PAIS
        /*sqlite.addPais("Bolivia");
        sqlite.addPais("Chile");
        sqlite.addPais("Bangladesh");*/
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
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                //Intent i = new Intent(LoginActivity.this, activityNavigation.class);
                //startActivity(i);
            }
        });

    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid user, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Store values at the time of the login attempt.
        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        Toast.makeText(this, user+" -- "+password,Toast.LENGTH_SHORT).show();
    }
}

