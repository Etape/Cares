package com.example.fujitsu.cares;

public class suivi_montre {
    String  Id_suivi;
    String  Id_patient;
    String  Id_medecin_debut;
    String  Id_bracelet;
    Long  date_debut;
    Long  date_fin;
    String  Id_medecin_fin;

    public String getId_suivi() {
        return Id_suivi;
    }

    public void setId_suivi(String id_suivi) {
        Id_suivi = id_suivi;
    }

    public String getId_patient() {
        return Id_patient;
    }

    public void setId_patient(String id_patient) {
        Id_patient = id_patient;
    }

    public String getId_medecin_debut() {
        return Id_medecin_debut;
    }

    public void setId_medecin_debut(String id_medecin_debut) {
        Id_medecin_debut = id_medecin_debut;
    }

    public String getId_bracelet() {
        return Id_bracelet;
    }

    public void setId_bracelet(String id_bracelet) {
        Id_bracelet = id_bracelet;
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

    public String getId_medecin_fin() {
        return Id_medecin_fin;
    }

    public void setId_medecin_fin(String id_medecin_fin) {
        Id_medecin_fin = id_medecin_fin;
    }
}
