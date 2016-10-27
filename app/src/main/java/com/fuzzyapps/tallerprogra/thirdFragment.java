package com.fuzzyapps.tallerprogra;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class thirdFragment extends Fragment {
    // Inputs
    private EditText year;
    private Button crear;
    // Instancia de SQLite
    private SQLite sqlite;

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
        sqlite= new SQLite(getActivity());
        sqlite.abrir();
        //AQUI INICIALIZAR LOS OBJETOS
        year = (EditText) view.findViewById(R.id.year);
        crear = (Button) view.findViewById(R.id.createGs);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Torneo creado", Toast.LENGTH_SHORT).show();
            }
        });

        sqlite.cerrar();
    }

}
