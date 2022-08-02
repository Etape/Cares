package com.example.fujitsu.cares;

import com.google.android.gms.tasks.Task;

public class bracelet {
    String  Id_bracelet;
    String Code;
    String etat="ETEINT";
    String activité ="null";
    String position ="null";
    String etablissement;

    public String getId_bracelet() {
        return Id_bracelet;
    }

    public void setId_bracelet(String id_bracelet) {
        Id_bracelet = id_bracelet;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getActivité() {
        return activité;
    }

    public void setActivité(String activité) {
        this.activité = activité;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public bracelet(String code, String etat, String activité, String position, String etablissement) {
        this.Code = code;
        this.etat = etat;
        this.activité = activité;
        this.position = position;
        this.etablissement = etablissement;
    }
}
