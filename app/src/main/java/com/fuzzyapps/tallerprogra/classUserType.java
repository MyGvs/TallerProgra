package com.fuzzyapps.tallerprogra;

/**
 * Created by Fernando on 1/11/2016.
 */

public class classUserType {
    private int idTipoPersona;
    private String tipoPersona;

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public classUserType(int idTipoPersona, String tipoPersona) {

        this.idTipoPersona = idTipoPersona;
        this.tipoPersona = tipoPersona;
    }
}
