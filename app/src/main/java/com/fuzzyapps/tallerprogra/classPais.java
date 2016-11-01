package com.fuzzyapps.tallerprogra;

/**
 * Created by Fernando on 1/11/2016.
 */

public class classPais {
    private int idPais;
    private String pais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public classPais(int idPais, String pais) {

        this.idPais = idPais;
        this.pais = pais;
    }
}
