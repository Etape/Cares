package com.example.fujitsu.cares;

public class patient {

    String  ID_patient;
    String  nom;
    String  prenom;
    String  email;
    String  telephone;
    String  age;
    String  sexe;
    String  urlPhoto;
    String  poids;
    String  groupe_sanguin;
    String  bracelet="null";
    Long  date_entree;
    Long  date_sortie;
    String  ID_hopital;

    public String getID_patient() {
        return ID_patient;
    }

    public void setID_patient(String ID_patient) {
        this.ID_patient = ID_patient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public String getID_hopital() {
        return ID_hopital;
    }

    public void setID_hopital(String ID_hopital) {
        this.ID_hopital = ID_hopital;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getGroupe_sanguin() {
        return groupe_sanguin;
    }

    public void setGroupe_sanguin(String groupe_sanguin) {
        this.groupe_sanguin = groupe_sanguin;
    }

    public String getBracelet() {
        return bracelet;
    }

    public void setBracelet(String bracelet) {
        this.bracelet = bracelet;
    }

    public Long getDate_entree() {
        return date_entree;
    }

    public void setDate_entree(Long date_entree) {
        this.date_entree = date_entree;
    }

    public Long getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Long date_sortie) {
        this.date_sortie = date_sortie;
    }

    public patient(String nom, String prenom, String email, String telephone, String age, String sexe, String urlPhoto, String poids, String groupe_sanguin, String bracelet, Long date_entree, Long date_sortie, String ID_hopital) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.sexe = sexe;
        this.urlPhoto = urlPhoto;
        this.poids = poids;
        this.groupe_sanguin = groupe_sanguin;
        this.bracelet = bracelet;
        this.date_entree = date_entree;
        this.date_sortie = date_sortie;
        this.ID_hopital = ID_hopital;
    }
}
