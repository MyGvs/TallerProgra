package com.fuzzyapps.tallerprogra;

/**
 * Created by Alvaro on 03/11/2016.
 */

public class classGrupo {
    private int idGrupo;
    private String grupo;
    private String pais;
    private String tipo;

    public classGrupo(int idGrupo, String grupo, String pais, String tipo) {
        this.idGrupo = idGrupo;
        this.grupo = grupo;
        this.pais = pais;
        this.tipo = tipo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
