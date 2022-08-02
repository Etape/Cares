package com.example.fujitsu.cares;

import java.io.File;

public class patientsElement {
    private patient patient;
    private  bracelet bracelet  ;
    private   lit lit;
    private File   file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public com.example.fujitsu.cares.patient getPatient() {
        return patient;
    }

    public void setPatient(com.example.fujitsu.cares.patient patient) {
        this.patient = patient;
    }

    public com.example.fujitsu.cares.bracelet getBracelet() {
        return bracelet;
    }

    public void setBracelet(com.example.fujitsu.cares.bracelet bracelet) {
        this.bracelet = bracelet;
    }

    public com.example.fujitsu.cares.lit getLit() {
        return lit;
    }

    public void setLit(com.example.fujitsu.cares.lit lit) {
        this.lit = lit;
    }
}
