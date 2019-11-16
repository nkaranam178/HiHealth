package com.dragonnetwork.hihealth.data;

import java.sql.Time;

public class Medication {
    private String medName;
    private String type;
    private String strength;
    private int doses;
    private String frequency;
    private int numPills;
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

    public int getDosage() {return doses; }

    public void setDosage(int dosage) { this.doses = dosage; }

    public String getStrength() {return strength; }

    public void setStrength(String strength) { this.strength = strength; }

    public String getType() {return type; }

    public void setType(String type) { this.type = type; }

    public String getFrequency() {return frequency; }

    public void setFrequency(String frequency) { this.frequency = frequency; }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    public Medication(String medName,String type, int numPills, String strength, int doses, String frequency ) {
        this.medName = medName;
        this.numPills = numPills;
        this.strength = strength;
        this.doses = doses;
        this.type = type;
        this.frequency = frequency;
        //this.time = time;

    }


}
