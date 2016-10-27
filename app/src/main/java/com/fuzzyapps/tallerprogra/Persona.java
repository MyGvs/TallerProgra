package com.fuzzyapps.tallerprogra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fernando on 25/10/2016.
 */

public class Persona {

    public boolean addPlayer(SQLiteDatabase db, SQLiteHelper sqliteHelper, String user, String pass, String name, String last_name1, String last_name2, String ci, String country, String genre, String type){
        if(user.length() > 0 &&
                pass.length() > 0 &&
                name.length() > 0 &&
                last_name1.length() > 0 &&
                last_name2.length() > 0 &&
                ci.length() > 0 &&
                country.length() > 0 &
                        genre.length() > 0 &&
                type.length() > 0){
            String[] countrySplit = country.split(".");
            String[] genreSplit = genre.split(".");
            String[] typeSplit = type.split(".");

            ContentValues contentValues = new ContentValues();
            contentValues.put( "usuario", user);
            contentValues.put( "clave", pass);
            contentValues.put( "nombre", name);
            contentValues.put( "apellido", last_name1+" "+last_name2);
            contentValues.put( "ci", ci);
            contentValues.put( "genero_idgenero", genreSplit[0]);
            contentValues.put( "pais_idpais", countrySplit[0]);
            contentValues.put( "tipo_persona_idTipoPersona", typeSplit[0]);

            Log.e("SQLite", "Nueva Persona " );
            //return ( db.insert( sqliteHelper.name_table_tipo_persona , null, contentValues ) != -1 )?true:false;
        }else{
            return false;
        }
        return true;
    }
}
