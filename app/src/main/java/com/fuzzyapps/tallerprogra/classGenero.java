package com.fuzzyapps.tallerprogra;

/**
 * Created by Fernando on 1/11/2016.
 */

public class classGenero {
    private int idGenero;
    private String genero;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public classGenero(int idGenero, String genero) {

        this.idGenero = idGenero;
        this.genero = genero;
    }
}
