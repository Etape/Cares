package com.example.fujitsu.cares;

public class personnel {
    String  ID_personnel;
    String  nom;
    String  prenom;
    String  email;
    String  mot_de_passe;
    String  poste;
    String  urlPhoto;
    String  etat;
    String  etablissement;
    String  telephone;

    public String getID_personnel() {
        return ID_personnel;
    }

    public void setID_personnel(String ID_personnel) {
        this.ID_personnel = ID_personnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public personnel(String nom, String prenom, String email, String mot_de_passe, String poste, String urlPhoto, String etat, String etablissement, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.poste = poste;
        this.urlPhoto = urlPhoto;
        this.etat = etat;
        this.etablissement = etablissement;
        this.telephone = telephone;
    }
}
