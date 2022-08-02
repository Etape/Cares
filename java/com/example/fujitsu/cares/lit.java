package com.example.fujitsu.cares;

public class lit {
    String  id;
    String  ID_hopital;
    String  chambre;
    float   Frequence_cardiaque= (float) 0.0;
    float  pouls= (float) 0.0;
    float  temperature= (float) 0.0;
    String  etat="VIDE";

    public String getID_hopital() {
        return ID_hopital;
    }

    public void setID_hopital(String ID_hopital) {
        this.ID_hopital = ID_hopital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChambre() {
        return chambre;
    }

    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    public float getFrequence_cardiaque() {
        return Frequence_cardiaque;
    }

    public void setFrequence_cardiaque(Float frequence_cardiaque) {
        Frequence_cardiaque = frequence_cardiaque;
    }

    public float getPouls() {
        return pouls;
    }

    public void setPouls(float pouls) {
        this.pouls = pouls;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}
