package com.fuzzyapps.tallerprogra;

/**
 * Created by Geovani on 03/11/2016.
 */

public class Match {
    public String idPartido;
    public int resultado_a;
    public int resultado_b;
    public String fecha;
    public String nombreGrupo1;
    public String nombreGrupo2;

    public Match(String idPartido, int resultado_a, int resultado_b, String fecha, String nombreGrupo1, String nombreGrupo2) {
        this.idPartido = idPartido;
        this.resultado_a = resultado_a;
        this.resultado_b = resultado_b;
        this.fecha = fecha;
        this.nombreGrupo1 = nombreGrupo1;
        this.nombreGrupo2 = nombreGrupo2;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public int getResultado_a() {
        return resultado_a;
    }

    public void setResultado_a(int resultado_a) {
        this.resultado_a = resultado_a;
    }

    public int getResultado_b() {
        return resultado_b;
    }

    public void setResultado_b(int resultado_b) {
        this.resultado_b = resultado_b;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreGrupo1() {
        return nombreGrupo1;
    }

    public void setNombreGrupo1(String nombreGrupo1) {
        this.nombreGrupo1 = nombreGrupo1;
    }

    public String getNombreGrupo2() {
        return nombreGrupo2;
    }

    public void setNombreGrupo2(String nombreGrupo2) {
        this.nombreGrupo2 = nombreGrupo2;
    }
}
