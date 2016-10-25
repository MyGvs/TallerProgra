package com.fuzzyapps.tallerprogra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    //nombre de la base de datos
    private static final String __DATABASE = "grandslam.db";
    //version de la base de datos
    private static final int __VERSION = 3;
    //Path of Data Base
    public String databasePath = "";
    // NOMBRE DE LAS TABLAS
    public final String name_table_pais = "pais";
    public final String name_table_torneo = "torneo";
    public final String name_table_grandSlam = "grandSlam";
    public final String name_table_modalidad = "modalidad";
    public final String name_table_torneoModalidad = "torneoModalidad";
    public final String name_table_genero = "genero";
    public final String name_table_tipo_persona = "tipo_persona";
    public final String name_table_persona = "persona";
    public final String name_table_grupo = "grupo";
    public final String name_table_personaGrupo = "personaGrupo";
    public final String name_table_inscripcion = "inscripcion";
    public final String name_table_lugarEncuentro = "lugarEncuentro";
    public final String name_table_partido = "partido";
    public final String name_table_premiosTorneo = "premiosTorneo";
    // QUERY PARA LAS TABLAS
    public final String sql_table_pais = "CREATE TABLE "+name_table_pais+"( " +
            "`idpais` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`pais` VARCHAR(45) )";
    public final String sql_table_torneo = "CREATE TABLE "+name_table_torneo+"( " +
            "`idtorneo` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`pais_idpais` INT NOT NULL, " +
            "FOREIGN KEY (`pais_idpais` ) REFERENCES `pais` (`idpais` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";
    public final String sql_table_grandSlam = "CREATE TABLE "+name_table_grandSlam+"( " +
            "`idgrandSlam` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`anio` INTEGER NOT NULL, " +
            "`torneo_idtorneo1` INTEGER NOT NULL, " +
            "`torneo_idtorneo2` INTEGER NOT NULL, " +
            "`torneo_idtorneo3` INTEGER NOT NULL, " +
            "`torneo_idtorneo4` INTEGER NOT NULL, " +
            "FOREIGN KEY (`torneo_idtorneo4` ) REFERENCES `torneo` (`idtorneo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`torneo_idtorneo1` ) REFERENCES `torneo` (`idtorneo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`torneo_idtorneo2` ) REFERENCES `torneo` (`idtorneo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`torneo_idtorneo3` ) REFERENCES `torneo` (`idtorneo` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";
    public final String sql_table_modalidad = "CREATE TABLE "+name_table_modalidad+"( " +
            "`idmodalidad` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`modalidad` VARCHAR(45) )";
    public final String sql_table_torneoModalidad = "CREATE TABLE "+name_table_torneoModalidad+"( " +
            "`idTorneoMod` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`torneo_idtorneo` INTEGER NOT NULL, " +
            "`modalidad_idmodalidad` INTEGER NOT NULL, " +
            "FOREIGN KEY (`torneo_idtorneo` ) REFERENCES `torneo` (`idtorneo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`modalidad_idmodalidad` ) REFERENCES `modalidad` (`idmodalidad` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";

    public final String sql_table_genero = "CREATE TABLE "+name_table_genero+"( " +
            "`idgenero` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`genero` VARCHAR(45) )";
    public final String sql_table_tipo_persona = "CREATE TABLE "+name_table_tipo_persona+"( " +
            "`idTipoPersona` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`tipo` VARCHAR(45) )";
    public final String sql_table_persona = "CREATE TABLE "+name_table_persona+"( " +
            "`idjugador` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`nombre` VARCHAR(45), " +
            "`apellido` VARCHAR(45), " +
            "`fecha_nacimiento` DATE, " +
            "`ci` VARCHAR(45), " +
            "`genero_idgenero` INTEGER NOT NULL, " +
            "`pais_idpais` INTEGER NOT NULL, " +
            "`tipo_persona_idTipoPersona` INTEGER NOT NULL, " +
            "`usuario` VARCHAR(45), " +
            "`clave` VARCHAR(100), " +
            "FOREIGN KEY (`genero_idgenero` ) REFERENCES `genero` (`idgenero` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`pais_idpais` ) REFERENCES `pais` (`idpais` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`tipo_persona_idTipoPersona` ) REFERENCES `tipo_persona` (`idTipoPersona` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";
    public final String sql_table_grupo = "CREATE TABLE "+name_table_grupo+"( " +
            "`idgrupo` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`nombre_grupo` VARCHAR(45) )";
    public final String sql_table_personaGrupo = "CREATE TABLE "+name_table_personaGrupo+"( " +
            "`idPersonaGrupo` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`persona_idjugador` INTEGER NOT NULL, " +
            "`grupo_idgrupo` INTEGER NOT NULL, " +
            "FOREIGN KEY (`persona_idjugador` ) REFERENCES `persona` (`idjugador` ) ON DELETE NO ACTION ON UPDATE NO ACTION, "+
            "FOREIGN KEY (`grupo_idgrupo` ) REFERENCES `grupo` (`idgrupo` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";

    public final String sql_table_inscripcion = "CREATE TABLE "+name_table_inscripcion+"( " +
            "`idinscripcion` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`fecha_inscripcion` DATE, " +
            "`grupo_idgrupo` INTEGER NOT NULL, " +
            "`pais_idpais` INTEGER NOT NULL, " +
            "FOREIGN KEY (`grupo_idgrupo` ) REFERENCES `grupo` (`idgrupo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, "+
            "FOREIGN KEY (`pais_idpais` ) REFERENCES `pais` (`idpais` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";
    public final String sql_table_lugarEncuentro = "CREATE TABLE "+name_table_lugarEncuentro+"( " +
            "`idlugarEncuentro` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`lugar` DATE, " +
            "`pais_idpais` INTEGER NOT NULL, " +
            "FOREIGN KEY (`pais_idpais` ) REFERENCES `pais` (`idpais` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";
    public final String sql_table_partido = "CREATE TABLE "+name_table_partido+"( " +
            "`idpartido` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`resultado_a` INTEGER NOT NULL, " +
            "`resultado_b` INTEGER NOT NULL, " +
            "`fecha` DATE, " +
            "`lugarEncuentro_idlugarEncuentro` INTEGER NOT NULL, " +
            "`grupo_idgrupo1` INTEGER NOT NULL, " +
            "`grupo_idgrupo2` INTEGER NOT NULL, " +
            "`grupo_idarbitros` INTEGER NOT NULL, " +
            "`torneoModalidad_idTorneoMod` INTEGER NOT NULL, " +
            "FOREIGN KEY (`lugarEncuentro_idlugarEncuentro` ) REFERENCES `lugarEncuentro` (`idlugarEncuentro` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`grupo_idarbitros` ) REFERENCES `grupo` (`idgrupo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`grupo_idgrupo1` ) REFERENCES `grupo` (`idgrupo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
            "FOREIGN KEY (`grupo_idgrupo2` ) REFERENCES `grupo` (`idgrupo` ) ON DELETE NO ACTION ON UPDATE NO ACTION, "+
            "FOREIGN KEY (`torneoModalidad_idTorneoMod` ) REFERENCES `torneoModalidad` (`idTorneoMod` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";
    public final String sql_table_premiosTorneo = "CREATE TABLE "+name_table_premiosTorneo+"( " +
            "`idpremiosTorneo` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`premio` DOUBLE, " +
            "`fase` VARCHAR(45), " +
            "`torneoModalidad_idTorneoMod` INTEGER NOT NULL, " +
            "FOREIGN KEY (`torneoModalidad_idTorneoMod` ) REFERENCES `torneoModalidad` (`idTorneoMod` ) ON DELETE NO ACTION ON UPDATE NO ACTION )";


    public SQLiteHelper(Context context) {
        super( context, __DATABASE, null, __VERSION );
        databasePath = context.getDatabasePath("grandslam.db").getPath();
    }
    public String getPath(){
        return databasePath;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

		db.execSQL(sql_table_pais);
		db.execSQL(sql_table_torneo);
		db.execSQL(sql_table_grandSlam);
		db.execSQL(sql_table_modalidad);
		db.execSQL(sql_table_torneoModalidad);

        db.execSQL(sql_table_genero);
        db.execSQL(sql_table_tipo_persona);
        db.execSQL(sql_table_persona);
        db.execSQL(sql_table_grupo);
        db.execSQL(sql_table_personaGrupo);

        db.execSQL(sql_table_inscripcion);
        db.execSQL(sql_table_lugarEncuentro);
        db.execSQL(sql_table_partido);
        db.execSQL(sql_table_premiosTorneo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        if ( newVersion > oldVersion ) {
            //elimina tabla

			db.execSQL( "DROP TABLE IF EXISTS " + sql_table_pais);
			db.execSQL( "DROP TABLE IF EXISTS " + sql_table_torneo);
			db.execSQL( "DROP TABLE IF EXISTS " + sql_table_grandSlam);
			db.execSQL( "DROP TABLE IF EXISTS " + sql_table_modalidad);
			db.execSQL( "DROP TABLE IF EXISTS " + sql_table_torneoModalidad);

            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_genero);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_tipo_persona);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_persona);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_grupo);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_personaGrupo);

            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_inscripcion);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_lugarEncuentro);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_partido);
            db.execSQL( "DROP TABLE IF EXISTS " + sql_table_premiosTorneo);
			//y luego creamos la nueva tabla

			db.execSQL(sql_table_pais);
			db.execSQL(sql_table_torneo);
			db.execSQL(sql_table_grandSlam);
			db.execSQL(sql_table_modalidad);
			db.execSQL(sql_table_torneoModalidad);

            db.execSQL(sql_table_genero);
            db.execSQL(sql_table_tipo_persona);
            db.execSQL(sql_table_persona);
            db.execSQL(sql_table_grupo);
            db.execSQL(sql_table_personaGrupo);

            db.execSQL(sql_table_inscripcion);
            db.execSQL(sql_table_lugarEncuentro);
            db.execSQL(sql_table_partido);
            db.execSQL(sql_table_premiosTorneo);
        }
    }

}