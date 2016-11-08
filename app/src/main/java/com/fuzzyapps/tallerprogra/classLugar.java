package com.fuzzyapps.tallerprogra;

/**
 * Created by Fernando on 3/11/2016.
 */

public class classLugar {
    private int idLugar;
    private String lugar;

    public classLugar(int idLugar, String lugar) {
        this.idLugar = idLugar;
        this.lugar = lugar;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
