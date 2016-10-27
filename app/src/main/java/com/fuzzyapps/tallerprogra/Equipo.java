package com.fuzzyapps.tallerprogra;

/**
 * Created by Geovani on 27/10/2016.
 */

public class Equipo {
    public String teamName;
    public int idIntegrante1;
    public int idIntegrante2;

    public Equipo(String teamName, int idIntegrante1) {
        this.teamName = teamName;
        this.idIntegrante1 = idIntegrante1;
    }

    public Equipo(String teamName, int idIntegrante1, int idIntegrante2) {
        this.teamName = teamName;
        this.idIntegrante1 = idIntegrante1;
        this.idIntegrante2 = idIntegrante2;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getIdIntegrante1() {
        return idIntegrante1;
    }

    public void setIdIntegrante1(int idIntegrante1) {
        this.idIntegrante1 = idIntegrante1;
    }

    public int getIdIntegrante2() {
        return idIntegrante2;
    }

    public void setIdIntegrante2(int idIntegrante2) {
        this.idIntegrante2 = idIntegrante2;
    }
}
