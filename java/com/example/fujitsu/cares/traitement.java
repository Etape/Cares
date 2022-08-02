package com.example.fujitsu.cares;

public class traitement {
    String  ID_traitement;
    String  ID_patient;
    String  mal;
    String  traitement;
    Long  date_debut;
    Long  date_fin;
    String  ID_medecin;
    String  ID_hopital;
    int  posologie;

    public String getID_traitement() {
        return ID_traitement;
    }

    public void setID_traitement(String ID_traitement) {
        this.ID_traitement = ID_traitement;
    }

    public String getID_patient() {
        return ID_patient;
    }

    public void setID_patient(String ID_patient) {
        this.ID_patient = ID_patient;
    }

    public String getMal() {
        return mal;
    }

    public void setMal(String mal) {
        this.mal = mal;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
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

    public int getPosologie() {
        return posologie;
    }

    public void setPosologie(int posologie) {
        this.posologie = posologie;
    }

    public void setDate_fin(Long date_fin) {
        this.date_fin = date_fin;
    }

    public String getID_medecin() {
        return ID_medecin;
    }

    public void setID_medecin(String ID_medecin) {
        this.ID_medecin = ID_medecin;
    }

    public String getID_hopital() {
        return ID_hopital;
    }

    public void setID_hopital(String ID_hopital) {
        this.ID_hopital = ID_hopital;
    }

    public traitement(String ID_traitement, String ID_patient, String mal, String traitement,int posologie, Long date_debut, Long date_fin, String ID_medecin, String ID_hopital) {
        this.ID_traitement = ID_traitement;
        this.ID_patient = ID_patient;
        this.mal = mal;
        this.posologie = posologie;
        this.traitement = traitement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.ID_medecin = ID_medecin;
        this.ID_hopital = ID_hopital;
    }
}
