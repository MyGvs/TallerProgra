package com.fuzzyapps.tallerprogra;

/**
 * Created by Alvaro on 03/11/2016.
 */

public class classGrupo {
    private int idGrupo;
    private String grupo;

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

    public classGrupo(int idGrupo, String grupo) {
        this.idGrupo = idGrupo;
        this.grupo = grupo;
    }
}
