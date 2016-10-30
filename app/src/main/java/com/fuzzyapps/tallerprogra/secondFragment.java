package com.fuzzyapps.tallerprogra;

import android.app.Fragment;
import android.database.Cursor;
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
    private SQLite sqlite;

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
        sqlite = new SQLite(getActivity());

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
        ArrayAdapter<String> adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        cargarArrayFemenino();
        adapterFemenino.notifyDataSetChanged();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    SQLite sqlite = new SQLite(getActivity());
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
        ArrayAdapter<String> adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        ArrayAdapter<String> adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteB.setAdapter(adapterMasculino);

        cargarArrayFemenino();
        cargarArrayMasculino();
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
        ArrayAdapter<String> adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteA.setAdapter(adapterMasculino);
        cargarArrayMasculino();
        adapterMasculino.notifyDataSetChanged();
        //agregar a los spinners las perosnas
        builder.setView(view);
        final AlertDialog alert = builder.create();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!teamName.getText().toString().equals("")){
                    String playerID[] = teamName.getText().toString().split("\\.");
                    sqlite.abrir();
                    sqlite.cerrar();

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
        ArrayAdapter<String> adapterFemenino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFemenino);
        integranteA.setAdapter(adapterFemenino);
        ArrayAdapter<String> adapterMasculino = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayMasculino);
        integranteB.setAdapter(adapterMasculino);

        cargarArrayFemenino();
        cargarArrayMasculino();
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

        cargarArrayFemenino();
        cargarArrayMasculino();
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
    private void cargarArrayFemenino() {
        arrayFemenino.clear();
        sqlite.abrir();
        try {
            Cursor cursor = sqlite.getAllPersonaFemenino();
            if( cursor.moveToFirst() ) {
                do {
                    arrayFemenino.add(cursor.getString(0)+". " +cursor.getString(1)+ " "+cursor.getString(2));
                    //Toast.makeText(getActivity(),cursor.getString(0)+" - "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        sqlite.cerrar();
    }
    private void cargarArrayMasculino() {
        arrayMasculino.clear();
        sqlite.abrir();
        try {
            Cursor cursor = sqlite.getAllPersonaMasculino();
            if( cursor.moveToFirst() ) {
                do {
                    arrayMasculino.add(cursor.getString(0)+". " +cursor.getString(1)+ " "+cursor.getString(2));
                    //Toast.makeText(getActivity(),cursor.getString(0)+" - "+cursor.getString(1)+" - "+cursor.getString(2)+" - "+cursor.getString(3)+" - "+cursor.getString(4)+" - "+cursor.getString(5)+" - "+cursor.getString(6)+" - "+cursor.getString(7)+" - "+cursor.getString(8)+" - "+cursor.getString(9),Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),cursor.getString(0)+" - "+cursor.getString(1)+" - "+cursor.getString(2)+" - "+cursor.getString(3)+" - "+cursor.getString(4),Toast.LENGTH_SHORT).show();
                } while ( cursor.moveToNext() );
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        sqlite.cerrar();
    }
}
