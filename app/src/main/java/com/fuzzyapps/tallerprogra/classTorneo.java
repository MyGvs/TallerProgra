package com.fuzzyapps.tallerprogra;

/**
 * Created by Fernando on 8/11/2016.
 */

public class classTorneo {
    private int idTorneo;
    private String pais;

    public classTorneo(int idTorneo, String pais) {
        this.idTorneo = idTorneo;
        this.pais = pais;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
