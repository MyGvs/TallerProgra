package com.fuzzyapps.tallerprogra;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class secondFragment extends Fragment {
    private Button individualF, doblesF, individualM, doblesM, doblesMix;
    private LayoutInflater layoutInflater;

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
    }
    private void desplegarDialogoDoblesFemenino() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES FEMENINO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
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
    }
    private void desplegarDialogoDoblesMasculino() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CREAR EQUIPO DOBLES MACULINO");
        View view = layoutInflater.inflate(R.layout.item_registro_dobles, null);
        Button register = (Button) view.findViewById(R.id.register);
        Spinner integranteA = (Spinner) view.findViewById(R.id.integranteA);
        Spinner integranteB = (Spinner) view.findViewById(R.id.integranteB);
        final EditText teamName = (EditText) view.findViewById(R.id.teamName);
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

}
