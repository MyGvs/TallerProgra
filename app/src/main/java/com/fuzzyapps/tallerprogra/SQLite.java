package com.fuzzyapps.tallerprogra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLite {

    private SQLiteHelper sqliteHelper;
    private SQLiteDatabase db;
    private String path = "";
    /** Constructor de clase */
    public SQLite(Context context){
        sqliteHelper = new SQLiteHelper( context );
        path = sqliteHelper.getPath();
    }
    /** Abre conexion a base de datos */
    public void abrir(){
        Log.i("SQLite", "Se abre conexion a la base de datos " + sqliteHelper.getDatabaseName() );
        db = sqliteHelper.getWritableDatabase();
    }
    public String getPath(){
        return path;
    }
    /** Cierra conexion a la base de datos */
    public void cerrar(){
        Log.i("SQLite", "Se cierra conexion a la base de datos " + sqliteHelper.getDatabaseName() );
        sqliteHelper.close();
    }

    // Obtener instancia de base de datos
    public SQLiteDatabase getDb(){
        return db;
    }

    // Obtener instancia de sqlite helper
    public SQLiteHelper getSqliteHelper(){
        return sqliteHelper;
    }
    // EJEMPLO DE QUERY
    public Cursor getAllPais(){
        //new
        String p_query = "SELECT idpais, pais FROM pais ORDER BY idpais";
        return db.rawQuery(p_query, null);
    }

    //EJEMPLO DE AGREGAR REGISTRO
    //EL NOMBRE DE LATABLA LA JALAN DE LA CLAS SQLiteHelper
    //SI SE HACE EL REGISTRO RETORNA TRUE
    //SI NO RETORNA FALSE
    //NO INGRESEN LOS IDS ESOS ESTAN TODOS EN AUTO INCREMENT
    public boolean addPais(String pais){
        //se comprueba que el largo de la variable "pais" es mayor a 0
        if( pais.length()> 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put( "pais", pais);
            Log.e("SQLite", "Nuevo pais " );
            return ( db.insert( sqliteHelper.name_table_pais , null, contentValues ) != -1 )?true:false;
        }
        else {
            return false;
        }
    }
    //PARA EL LOGIN
    //FALTA LOGIN EN LA TABLA DE USUARIO CREO
    public Cursor getAllPersonaMasculino(){
        String masculino = "Masculino";
        String p_query = "SELECT a.idjugador, a.nombre, a.apellido, a.ci, b.genero, c.tipo FROM persona a, genero b, tipo_persona c WHERE a.genero_idgenero = b.idgenero AND a.tipo_persona_idTipoPersona = c.idTipoPersona AND c.tipo = '"+masculino+"'";
        //String p_query = "SELECT * FROM persona";
        Log.e("Masculino","getting masculinos");
        return db.rawQuery(p_query, null);
    }
    public Cursor getAllPersonaFemenino(){
        String p_query = "SELECT a.idjugador, a.nombre, a.apellido, a.ci, b.genero, c.tipo FROM persona a, genero b, tipo_persona c WHERE a.genero_idgenero = b.idgenero AND a.tipo_persona_idTipoPersona = c.idTipoPersona AND c.tipo = 'Femenino' ORDER BY a.idjugador";
        return db.rawQuery(p_query, null);
    }
    public Cursor getAllPersona(){
        String p_query = "SELECT idjugador, nombre, apellido, fecha_nacimiento, ci, b.genero, c.tipo FROM persona a, genero b, tipo_persona c WHERE a.genero_idgenero = b.idgenero AND a.tipo_persona_idTipoPersona = c.idTipoPersona ORDER BY idjugador";
        return db.rawQuery(p_query, null);
    }
    public boolean addModalidad(String modalidad){
        if( modalidad.length()> 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put( "modalidad", modalidad);
            Log.e("SQLite", "Nueva Modalidad" );
            return ( db.insert( sqliteHelper.name_table_modalidad , null, contentValues ) != -1 )?true:false;
        }else {
            return false;
        }
    }
    public boolean addIndividualFemenino(Equipo equipo){
        ContentValues contentValues = new ContentValues();
        //contentValues.put( "modalidad", modalidad);
        Log.e("SQLite", "Nueva Modalidad" );
        return ( db.insert( sqliteHelper.name_table_modalidad , null, contentValues ) != -1 )?true:false;
    }

    // Generos
    public Cursor getAllGenre(){
        //new
        String p_query = "SELECT idGenero, genero FROM genero ORDER BY idGenero";
        return db.rawQuery(p_query, null);
    }

    public boolean addGenre(String genre){
        //se comprueba que el largo de la variable "pais" es mayor a 0
        if( genre.length()> 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put( "genero", genre);
            Log.e("SQLite", "Nuevo genero " );
            return ( db.insert( sqliteHelper.name_table_genero , null, contentValues ) != -1 )?true:false;
        }
        else {
            return false;
        }
    }

    // Tipos de usuario
    public Cursor getAllUserType(){
        //new
        String p_query = "SELECT idTipoPersona, tipo FROM tipo_persona ORDER BY idTipoPersona";
        return db.rawQuery(p_query, null);
    }

    public boolean addUserType(String userType){
        //se comprueba que el largo de la variable "pais" es mayor a 0
        if( userType.length()> 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put( "tipo", userType);
            Log.e("SQLite", "Nueva persona " );
            return ( db.insert( sqliteHelper.name_table_tipo_persona , null, contentValues ) != -1 )?true:false;
        }
        else {
            return false;
        }
    }

    // Agregar persona
    public boolean addPlayer(String user, String pass, String name, String last_name1, String last_name2, String ci, String country, String genre, String type){
        if(user.length() > 0 &&
                pass.length() > 0 &&
                name.length() > 0 &&
                last_name1.length() > 0 &&
                last_name2.length() > 0 &&
                ci.length() > 0 &&
                country.length() > 0 &
                        genre.length() > 0 &&
                type.length() > 0){
            String[] countrySplit = country.split("\\.");
            String[] genreSplit = genre.split("\\.");
            String[] typeSplit = type.split("\\.");
            ContentValues contentValues = new ContentValues();
            contentValues.put( "usuario", user);
            contentValues.put( "clave", pass);
            contentValues.put( "nombre", name);
            contentValues.put( "apellido", last_name1+" "+last_name2);
            contentValues.put( "ci", ci);
            contentValues.put( "genero_idgenero", genreSplit[0]);
            contentValues.put( "pais_idpais", countrySplit[0]);
            contentValues.put( "tipo_persona_idTipoPersona", typeSplit[0]);

            Log.e("SQLite", "Nuevoa persona " );
            return ( db.insert( sqliteHelper.name_table_persona , null, contentValues ) != -1 )?true:false;
        }else{
            return false;
        }
    }


    public boolean addGrupo(Equipo equipo){
        //se comprueba que el largo de la variable "pais" es mayor a 0
        if( equipo.getTeamName().length()> 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put( "nombre_grupo", equipo.getTeamName());
            Log.e("SQLite", "Nueva persona " );
            return ( db.insert( sqliteHelper.name_table_grupo , null, contentValues ) != -1 )?true:false;
        }
        else {
            return false;
        }
    }
    public boolean addGruppoIndividual(Equipo equipo, int idGrupo){
        //se comprueba que el largo de la variable "pais" es mayor a 0
        if( equipo.getTeamName().length()> 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put( "persona_idjugador", equipo.getTeamName());
            contentValues.put( "greupo_idgrupo", equipo.getIdIntegrante1());
            contentValues.put( "greupo_idgrupo", idGrupo);
            Log.e("SQLite", "Nueva persona " );
            return ( db.insert( sqliteHelper.name_table_grupo , null, contentValues ) != -1 )?true:false;
        }else {
            return false;
        }
    }
    public boolean addGrupoDobles(Equipo equipo, int idGrupo){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("persona_idjugador", equipo.getIdIntegrante1());
            contentValues.put("greupo_idgrupo", idGrupo);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("persona_idjugador", equipo.getIdIntegrante2());
            contentValues2.put("greupo_idgrupo", idGrupo);
            Log.e("SQLite", "Nueva persona ");
            db.insert(sqliteHelper.name_table_grupo, null, contentValues);
            db.insert(sqliteHelper.name_table_grupo, null, contentValues2);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}