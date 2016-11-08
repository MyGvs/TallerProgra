package com.fuzzyapps.tallerprogra;

/**
 * Created by Fernando on 8/11/2016.
 */

public class classFase {
    private int idFase;
    private String fase;

    public classFase(int idFase, String fase) {
        this.idFase = idFase;
        this.fase = fase;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }
}
