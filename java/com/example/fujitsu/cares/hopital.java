package com.example.fujitsu.cares;

public class hopital {
    String  ID_hopital;
    String  nom_hopital;
    String  ville;
    String  pays;
    String  quartier;
    String  email_hopital;
    String  telephone1;
    String  telephone2;

    public String getNom_hopital() {
        return nom_hopital;
    }

    public void setNom_hopital(String nom_hopital) {
        this.nom_hopital = nom_hopital;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getEmail_hopital() {
        return email_hopital;
    }

    public void setEmail_hopital(String email_hopital) {
        this.email_hopital = email_hopital;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getID_hopital() {
        return ID_hopital;
    }

    public void setID_hopital(String ID_hopital) {
        this.ID_hopital = ID_hopital;
    }

    public hopital(String nom_hopital, String ville, String pays, String quartier, String email_hopital, String telephone1, String telephone2) {
        this.nom_hopital = nom_hopital;
        this.ville = ville;
        this.pays = pays;
        this.quartier = quartier;
        this.email_hopital = email_hopital;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }
}
