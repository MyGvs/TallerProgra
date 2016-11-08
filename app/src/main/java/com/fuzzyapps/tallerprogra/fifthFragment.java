package com.fuzzyapps.tallerprogra;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class fifthFragment extends Fragment {
    private int selectedItem;
    private RecyclerView mRecyclerView;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LayoutInflater layoutInflater;
    private Spinner modalidad, fase;
    private ArrayList<String> arrayModalidad = new ArrayList<String>();
    private ArrayList<String> arrayFase = new ArrayList<String>();
    private Button buscar;
    Connection con;
    ArrayList<Match> matchesArrayList = new ArrayList<>();
    public fifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fifth_fragment, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutInflater = getActivity().getLayoutInflater();
        buscar = (Button) view.findViewById(R.id.buscar);
        modalidad = (Spinner) view.findViewById(R.id.modalidad);
        fase = (Spinner) view.findViewById(R.id.fase);
        //AQUI INICIALIZAR LOS OBJETOS
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MatchAdapter(matchesArrayList);
        mRecyclerView.setAdapter(mAdapter);
        /*FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        arrayFase.add("1. Campeon");
        arrayFase.add("2. Final");
        arrayFase.add("3. Semifinal");
        arrayFase.add("4. Cuartos de final");
        arrayFase.add("5. Octavos de final");
        arrayFase.add("6. Clasificatoria");
        ArrayAdapter<String> adapterFase = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayFase);
        fase.setAdapter(adapterFase);

        arrayModalidad.add("1. Individual Femenino");
        arrayModalidad.add("2. Individual Masculino");
        arrayModalidad.add("3. Dobles Femenino");
        arrayModalidad.add("4. Dobles Masculino");
        arrayModalidad.add("5. Dobles Mixtos");
        ArrayAdapter<String> adapterModalidad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayModalidad);
        modalidad.setAdapter(adapterModalidad);
        fase.setSelection(5);


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchesArrayList.clear();
                listarTodos();
                String modalidadSplit[] = modalidad.getSelectedItem().toString().split("\\.");
                String faseSplit[] = fase.getSelectedItem().toString().split("\\.");
                //Toast.makeText(getActivity(), modalidadSplit[0] + " - " + faseSplit[0], Toast.LENGTH_SHORT).show();
                new getPartida(Integer.parseInt(modalidadSplit[0]), Integer.parseInt(faseSplit[0])).execute();
            }
        });
        /*fase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                String modalidadSplit[] = modalidad.getSelectedItem().toString().split("\\.");
                String faseSplit[] = fase.getItemAtPosition(position).toString().split("\\.");
                //Toast.makeText(getActivity(), modalidadSplit[0] + " - " + faseSplit[0], Toast.LENGTH_SHORT).show();
                new getPartida(Integer.parseInt(modalidadSplit[0]), Integer.parseInt(faseSplit[0])).execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
    class getPartida extends AsyncTask<Void, Void, String> {
        int idModalidad, idFase;
        public getPartida(int idModalidad, int idFase){
            this.idModalidad = idModalidad;
            this.idFase = idFase;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            matchesArrayList.clear();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String driver = "oracle.jdbc.driver.OracleDriver";
            String UserName = "tallerprogra";
            String Password = "navia2016 ";
            String sourceURL = "jdbc:oracle:thin:@200.105.212.50:1521:xe";
            String cadena = "SELECT a.idpartido, a.fecha, a.resultado_a, a.resultado_b, b.nombre as gp1, b2.nombre as gp2 " +
                    "FROM gfa_partido a, gfa_grupo b, gfa_grupo b2, gfa_modalidad c, gfa_fase d " +
                    "WHERE a.idgrupoA = b.idgrupo AND a.idgrupoB = b2.idgrupo AND b.idmodalidad = c.idmodalidad AND c.idmodalidad = "+idModalidad+" AND a.idfase = d.idfase AND d.idfase = "+idFase+" ";
            try {
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL, UserName, Password);
                Statement st = con.createStatement();
                ResultSet resultado = st.executeQuery(cadena);
                while (resultado.next()) {
                    Match match = new Match(
                            resultado.getString("idpartido"),
                            resultado.getInt("resultado_a"),
                            resultado.getInt("resultado_b"),
                            resultado.getString("fecha"),
                            resultado.getString("gp1"),
                            resultado.getString("gp2")
                            );
                    matchesArrayList.add(match);
                }
                resultado.close();
                st.close();
                con.close();
                result = "ok";
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
                result = "";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("ok")){
                listarTodos();
            }
        }
    }
    private void listarTodos() {
        mAdapter.notifyDataSetChanged();
    }
    public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
        private ArrayList<Match> adapterMatch = new ArrayList<>();
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            //parse all textViews
            TextView fechaEncuentro, team1, team2, resultado2, resultado1, vicder1, vicder2;
            Button registrar;
            public ViewHolder(View v) {
                super(v);
                //get textviews
                fechaEncuentro = (TextView) v.findViewById(R.id.fechaEncuentro);
                team1 = (TextView) v.findViewById(R.id.team1);
                team2 = (TextView) v.findViewById(R.id.team2);
                resultado1 = (TextView) v.findViewById(R.id.resultado1);
                resultado2 = (TextView) v.findViewById(R.id.resultado2);
                vicder1 = (TextView) v.findViewById(R.id.vicder1);
                vicder2 = (TextView) v.findViewById(R.id.vicder2);
                registrar = (Button) v.findViewById(R.id.registrar);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MatchAdapter(ArrayList<Match> matchArray) {
            this.adapterMatch.clear();
            this.adapterMatch = matchArray;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_game_card, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MatchAdapter.ViewHolder vh = new MatchAdapter.ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final MatchAdapter.ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem = position;
                    showModifyDialog(adapterMatch.get(position).getIdPartido(), adapterMatch.get(position).getNombreGrupo1(), adapterMatch.get(position).getNombreGrupo2());
                }
            });
            holder.fechaEncuentro.setText(adapterMatch.get(position).getFecha());
            holder.team1.setText(adapterMatch.get(position).getNombreGrupo1());
            holder.team2.setText(adapterMatch.get(position).getNombreGrupo2());
            holder.resultado1.setText(adapterMatch.get(position).getResultado_a()+"");
            holder.resultado2.setText(adapterMatch.get(position).getResultado_b()+"");
            if(adapterMatch.get(position).getResultado_a() != (-1) && adapterMatch.get(position).getResultado_b() != (-1)){
                Log.e(adapterMatch.get(position).getIdPartido()+"/", adapterMatch.get(position).getResultado_a()+" es distinto de -1 ");
                if(adapterMatch.get(position).getResultado_a() == adapterMatch.get(position).getResultado_b()){
                    Log.e(adapterMatch.get(position).getIdPartido()+"/", adapterMatch.get(position).getResultado_a()+" - "+adapterMatch.get(position).getResultado_b());
                    holder.vicder1.setText("EMPATE");
                    holder.vicder1.setVisibility(View.VISIBLE);
                    holder.resultado1.setVisibility(View.VISIBLE);
                    holder.vicder1.setTextColor(Color.parseColor("#f9a825"));
                    holder.resultado1.setTextColor(Color.parseColor("#f9a825"));

                    holder.vicder2.setText("EMPATE");
                    holder.vicder2.setVisibility(View.VISIBLE);
                    holder.resultado2.setVisibility(View.VISIBLE);
                    holder.vicder2.setTextColor(Color.parseColor("#f9a825"));
                    holder.resultado2.setTextColor(Color.parseColor("#f9a825"));
                }else{
                    if(adapterMatch.get(position).getResultado_a() > adapterMatch.get(position).getResultado_b()){
                        Log.e(adapterMatch.get(position).getIdPartido()+"/", adapterMatch.get(position).getResultado_a() +" > "+adapterMatch.get(position).getResultado_b());
                        holder.vicder1.setText("VICTORIA");;
                        holder.vicder1.setVisibility(View.VISIBLE);
                        holder.resultado1.setVisibility(View.VISIBLE);
                        holder.vicder1.setTextColor(Color.parseColor("#3ADF00"));
                        holder.resultado1.setTextColor(Color.parseColor("#3ADF00"));

                        holder.vicder2.setText("DERROTA");
                        holder.vicder2.setVisibility(View.VISIBLE);
                        holder.resultado2.setVisibility(View.VISIBLE);
                        holder.vicder2.setTextColor(Color.parseColor("#d32f2f"));
                        holder.resultado2.setTextColor(Color.parseColor("#d32f2f"));
                    }else {
                        Log.e(adapterMatch.get(position).getIdPartido()+"/", adapterMatch.get(position).getResultado_a() +" < "+adapterMatch.get(position).getResultado_b());
                        holder.vicder1.setText("DERROTA");
                        holder.vicder1.setVisibility(View.VISIBLE);
                        holder.resultado1.setVisibility(View.VISIBLE);
                        holder.vicder1.setTextColor(Color.parseColor("#d32f2f"));
                        holder.resultado1.setTextColor(Color.parseColor("#d32f2f"));

                        holder.vicder2.setText("VICTORIA");
                        holder.vicder2.setVisibility(View.VISIBLE);
                        holder.resultado2.setVisibility(View.VISIBLE);
                        holder.vicder2.setTextColor(Color.parseColor("#3ADF00"));
                        holder.resultado2.setTextColor(Color.parseColor("#3ADF00"));
                    }

                }
            }else{
                Log.e(adapterMatch.get(position).getIdPartido()+"/", adapterMatch.get(position).getResultado_a()+" es igual a -1 ");
                holder.vicder1.setVisibility(View.GONE);
                holder.resultado1.setVisibility(View.GONE);

                holder.vicder2.setVisibility(View.GONE);
                holder.resultado2.setVisibility(View.GONE);
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return adapterMatch.size();
        }
    }
    class registrarCambio extends AsyncTask<Void, Void, String> {
        String resultado_a, resultado_b, idpartida;
        public registrarCambio(String resultado_a, String resultado_b, String idpartida){
            this.resultado_a = resultado_a;
            this.resultado_b = resultado_b;
            this.idpartida = idpartida;
        }
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
            String cadena = "UPDATE gfa_partido " +
                            "SET resultado_a="+resultado_a+", resultado_b="+resultado_b+ " "+
                            "WHERE idpartido="+idpartida;
            try {
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(sourceURL, UserName, Password);
                Statement st = con.createStatement();
                st.executeQuery(cadena);
                st.close();
                con.close();
                result = "ok";
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
                result = "";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("ok")){
                matchesArrayList.get(selectedItem).setResultado_a(Integer.parseInt(resultado_a));
                matchesArrayList.get(selectedItem).setResultado_b(Integer.parseInt(resultado_b));
                listarTodos();
                /*String modalidadSplit[] = modalidad.getSelectedItem().toString().split("\\.");
                String faseSplit[] = fase.getSelectedItem().toString().split("\\.");
                new getPartida(Integer.parseInt(modalidadSplit[0]), Integer.parseInt(faseSplit[0])).execute();*/
                //new getPartida("").execute();

            }
        }
    }
    private void showModifyDialog(final String idPartido, String grupo_a, String grupo_b) {
        //Toast.makeText(getActivity(), ""+idPartido, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("REGISTRAR RESULTADOS");
        View view = layoutInflater.inflate(R.layout.item_resultado, null);
        Button register = (Button) view.findViewById(R.id.register);
        final EditText team_a = (EditText) view.findViewById(R.id.team_a);
        //team_a.setHint("Resultado de: "+ grupo_a);
        final EditText team_b = (EditText) view.findViewById(R.id.team_b);
        //team_b.setHint("Resultado de: "+ grupo_b);
        builder.setView(view);
        builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(!team_a.getText().toString().equals("") && !team_b.getText().toString().equals("")){
                    new registrarCambio(team_a.getText().toString(), team_b.getText().toString(), idPartido).execute();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar ambos resultados",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }
}
