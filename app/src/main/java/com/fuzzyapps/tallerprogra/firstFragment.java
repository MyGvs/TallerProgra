package com.fuzzyapps.tallerprogra;


import android.app.DatePickerDialog;
import android.database.Cursor;
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

import java.util.ArrayList;

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

    // Instancia de SQLite
    private SQLite sqlite;

    // Instancias de Clases
    private Persona persona;

    // Spinner arrays
    private ArrayList<String> arraySpinnerCountry;
    private ArrayList<String> arraySpinnerGenre;
    private ArrayList<String> arraySpinnerUserType;

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
        sqlite= new SQLite(getActivity());
        sqlite.abrir();

        //AQUI INICIALIZAR LOS OBJETOS
        user = (EditText) view.findViewById(R.id.user);
        password = (EditText) view.findViewById(R.id.user);
        name = (EditText) view.findViewById(R.id.user);
        last_name1 = (EditText) view.findViewById(R.id.user);
        last_name2 = (EditText) view.findViewById(R.id.user);
        ci = (EditText) view.findViewById(R.id.user);
        country = (Spinner) view.findViewById(R.id.country);
        genre = (Spinner) view.findViewById(R.id.genre);
        userType = (Spinner) view.findViewById(R.id.userType);
        registerButton = (Button) view.findViewById(createPlayer);

        // INICIALIZACION DE ARRAYS
        this.arraySpinnerCountry = new ArrayList<String>();
        this.arraySpinnerGenre = new ArrayList<String>();
        this.arraySpinnerUserType = new ArrayList<String>();

        //EJEMPLO PARA AGREGAR UN PAIS
        /*sqlite.addPais("Bolivia");
        sqlite.addPais("Chile");
        sqlite.addPais("Bangladesh");*/

        // CARGAR PAISES
        try {
            Cursor cursor = sqlite.getAllPais();
            if( cursor.moveToFirst() ) {
                do {
                    // 0: idpais, 1: pais
                    //el numero debe ser correlativo a lo que usieron en el SELECT del query
                    //si es entero usan  cursor.getInt()
                    // si es varchar usan
                    //en este caso el id es el sub 0 y es un int y el pais es un varchar uso el getString()
                    //Toast.makeText(getActivity(),cursor.getInt(0)+" - "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                    this.arraySpinnerCountry.add(cursor.getInt(0)+"."+cursor.getString(1));
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerCountry);
        country.setAdapter(countryAdapter);

        //EJEMPLO PARA AGREGAR UN GENERO
        /*sqlite.addGenre("Masculino");
        sqlite.addGenre("Femenino");*/

        // CARGAR GENEROS
        try {
            Cursor cursor = sqlite.getAllGenre();
            if( cursor.moveToFirst() ) {
                do {
                    // 0: idpais, 1: pais
                    //el numero debe ser correlativo a lo que usieron en el SELECT del query
                    //si es entero usan  cursor.getInt()
                    // si es varchar usan
                    //en este caso el id es el sub 0 y es un int y el pais es un varchar uso el getString()
                    //Toast.makeText(getActivity(),cursor.getInt(0)+" - "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                    this.arraySpinnerGenre.add(cursor.getInt(0)+"."+cursor.getString(1));
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerGenre);
        genre.setAdapter(genreAdapter);

        //EJEMPLO PARA AGREGAR UN TIPO
        /*sqlite.addUserType("Jugador");
        sqlite.addUserType("Arbitro");
        sqlite.addUserType("Administrador");*/

        // CARGAR TIPOS DE USUARIO
        try {
            Cursor cursor = sqlite.getAllUserType();
            if( cursor.moveToFirst() ) {
                do {
                    // 0: idpais, 1: pais
                    //el numero debe ser correlativo a lo que usieron en el SELECT del query
                    //si es entero usan  cursor.getInt()
                    // si es varchar usan
                    //en este caso el id es el sub 0 y es un int y el pais es un varchar uso el getString()
                    //Toast.makeText(getActivity(),cursor.getInt(0)+" - "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                    this.arraySpinnerUserType.add(cursor.getInt(0)+"."+cursor.getString(1));
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerUserType);
        userType.setAdapter(userTypeAdapter);

        // Crear evento onClick de registrar
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Registro de jugador
                String formUser = user.getText().toString();
                String formPass = password.getText().toString();
                String formName = name.getText().toString();
                String formLastName1 = last_name1.getText().toString();
                String formLastName2 = last_name2.getText().toString();
                String formCI = ci.getText().toString();
                String formCountry = country.getSelectedItem().toString();
                String formGenre = genre.getSelectedItem().toString();
                String formType = userType.getSelectedItem().toString();
                persona.addPlayer(sqlite.getDb(),sqlite.getSqliteHelper(), formUser, formPass, formName, formLastName1, formLastName2, formCI, formCountry, formGenre, formType);
                //Toast.makeText(getActivity(),user.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        sqlite.cerrar();
    }

}
