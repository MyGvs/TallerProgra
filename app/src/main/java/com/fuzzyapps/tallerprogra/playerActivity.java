package com.fuzzyapps.tallerprogra;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class playerActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
        layoutInflater = getLayoutInflater();
        buscar = (Button) findViewById(R.id.buscar);
        modalidad = (Spinner) findViewById(R.id.modalidad);
        fase = (Spinner) findViewById(R.id.fase);
        //AQUI INICIALIZAR LOS OBJETOS
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MatchAdapter(matchesArrayList);
        mRecyclerView.setAdapter(mAdapter);
        getSupportActionBar().setTitle("Bienvenid@, "+Globals.nombre+" "+Globals.apellido);
        //Toast.makeText(this,Globals.idPersona+" - "+Globals.nombre+" - "+Globals.apellido,Toast.LENGTH_SHORT).show();
        new getPartida().execute();
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
            holder.registrar.setVisibility(View.GONE);
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
                        holder.vicder1.setText("VICTORIA");
                        holder.vicder1.setVisibility(View.VISIBLE);
                        holder.resultado1.setVisibility(View.VISIBLE);
                        holder.vicder1.setTextColor(Color.parseColor("#31B404"));
                        holder.resultado1.setTextColor(Color.parseColor("#31B404"));

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
                        holder.vicder2.setTextColor(Color.parseColor("#31B404"));
                        holder.resultado2.setTextColor(Color.parseColor("#31B404"));
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
    class getPartida extends AsyncTask<Void, Void, String> {
        public getPartida(){
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
            String cadena = "SELECT distinct a.idpartido, a.fecha, a.resultado_a, a.resultado_b, b.nombre as gp1, b2.nombre as gp2, c.fase " +
                    "FROM gfa_partido a, gfa_grupo b, gfa_grupo b2, gfa_fase c, gfa_perGrupo d, gfa_persona e " +
                    "WHERE a.idgrupoA = b.idgrupo AND a.idgrupoB = b2.idgrupo AND a.idfase = c.idfase AND d.idpersona = e.idpersona AND d.idgrupo = b.idgrupo  AND e.idpersona = "+Globals.idPersona+" ORDER BY a.idpartido DESC";
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
                            resultado.getString("fecha")+" ("+resultado.getString("fase")+")",
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
}
