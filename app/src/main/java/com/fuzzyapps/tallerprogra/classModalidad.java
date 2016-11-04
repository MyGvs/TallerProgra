package com.fuzzyapps.tallerprogra;

/**
 * Created by Alvaro on 03/11/2016.
 */

public class classModalidad {
    private int idModalidad;
    private String modalidad;

    public int getIdModalidad() {
        return idModalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setIdModalidad(int idModalidad) {
        this.idModalidad = idModalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public classModalidad(int idModalidad, String modalidad) {
        this.idModalidad = idModalidad;
        this.modalidad = modalidad;
    }
}
