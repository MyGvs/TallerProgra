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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


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

    // Instancia de SQLite
    private SQLite sqlite;

    // Spinner arrays
    private ArrayList<String> arraySpinnerCountry;

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

        //EJEMPLO PARA AGREGAR UN PAIS
        /*sqlite.addPais("Bolivia");
        sqlite.addPais("Chile");
        sqlite.addPais("Bangladesh");*/
        //EJEMPLO PARA LISTAR LOS PAISES
        this.arraySpinnerCountry = new ArrayList<String>();
        int i = 0;
        try {
            Cursor cursor = sqlite.getAllPais();
            if( cursor.moveToFirst() ) {
                do {
                    // 0: idpais, 1: pais
                    //el numero debe ser correlativo a lo que usieron en el SELECT del query
                    //si es entero usan  cursor.getInt()
                    // si es varchar usan
                    //en este caso el id es el sub 0 y es un int y el pais es un varchar uso el getString()
                    Toast.makeText(getActivity(),cursor.getInt(0)+" - "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                    this.arraySpinnerCountry.add(cursor.getInt(0)+"."+cursor.getString(1));
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerCountry);
        country.setAdapter(adapter);
        sqlite.cerrar();
    }

}
