package com.fuzzyapps.tallerprogra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
            return ( db.insert( sqliteHelper.name_table_persona , null, contentValues ) != -1 )?true:false;
        }
        else {
            return false;
        }
    }
    //PARA EL LOGIN
    //FALTA LOGIN EN LA TABLA DE USUARIO CREO

}