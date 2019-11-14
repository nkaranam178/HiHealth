package com.dragonnetwork.hihealth.data;

import java.sql.Time;

public class Medication {
    private String medName;
    private int numPills;
    private String dosage;
    private Time time;

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getNumPills() {
        return numPills;
    }

    public void setNumPills(int numPills) {
        this.numPills = numPills;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Medication(String medName, int numPills, String dosage, Time time) {
        this.medName = medName;
        this.numPills = numPills;
        this.dosage = dosage;
        this.time = time;
    }


}
