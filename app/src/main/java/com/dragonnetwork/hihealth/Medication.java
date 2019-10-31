package com.dragonnetwork.hihealth;

import java.sql.Time;

public class Medication {
    private String medName;
    private int numPills;
    private String dosage;
    private Time time;

    Medication() {}

    Medication(String medName, int numPills, String dosage, Time time) {
        this.medName = medName;
        this.numPills = numPills;
        this.dosage = dosage;
        this.time = time;
    }

    String getMedName() { return this.medName; }
    int getNumPills() { return this.numPills; }
    String getDosage() { return this.dosage; }
    Time getTime() { return this.time; }

    void setMedName(String medName) { this.medName = medName; }
    void setNumPills(int numPills) { this.numPills = numPills; }
    void setDosage(String dosage) { this.dosage = dosage; }
    void setTime(Time time) { this.time = time; }
}
