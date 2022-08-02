package com.example.fujitsu.cares;

public class internement {

    String  ID_internement;
    String  ID_traitement;
    static String  ID_lit;
    Long  date_debut;
    Long  date_fin;
    String  ID_patient;

    public String getID_internement() {
        return ID_internement;
    }

    public void setID_internement(String ID_internement) {
        this.ID_internement = ID_internement;
    }

    public String getID_traitement() {
        return ID_traitement;
    }

    public void setID_traitement(String ID_traitement) {
        this.ID_traitement = ID_traitement;
    }

    public static String getID_lit() {
        return ID_lit;
    }

    public void setID_lit(String ID_lit) {
        this.ID_lit = ID_lit;
    }

    public Long getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Long date_debut) {
        this.date_debut = date_debut;
    }

    public Long getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Long date_fin) {
        this.date_fin = date_fin;
    }

    public String getID_patient() {
        return ID_patient;
    }

    public void setID_patient(String ID_patient) {
        this.ID_patient = ID_patient;
    }

    public internement(String ID_traitement, String ID_lit, Long date_debut, Long date_fin, String ID_patient) {
        this.ID_traitement = ID_traitement;
        this.ID_lit = ID_lit;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.ID_patient = ID_patient;
    }
}
