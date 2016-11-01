package com.fuzzyapps.tallerprogra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fernando on 25/10/2016.
 */

public class Persona {
    public int idJugador;
    public String nombre;
    public String apellido;
    public String fecha_nacimiento;
    public String ci;
    public int idGenero;
    public int idPais;
    public int idTipoPersona;
    public String usuario;
    public String clave;

    public Persona(int idJugador, String nombre, String apellido, String fecha_nacimiento, String ci, int idGenero, int idPais, int idTipoPersona, String usuario, String clave) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.ci = ci;
        this.idGenero = idGenero;
        this.idPais = idPais;
        this.idTipoPersona = idTipoPersona;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Persona(String nombre, String apellido, String fecha_nacimiento, String ci, int idGenero, int idPais, int idTipoPersona, String usuario, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.ci = ci;
        this.idGenero = idGenero;
        this.idPais = idPais;
        this.idTipoPersona = idTipoPersona;
        this.usuario = usuario;
        this.clave = clave;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
