package com.dragonnetwork.hihealth.data;

import java.sql.Timestamp;

public class Appointment {
    private String Location;
    private Timestamp Begin;
    private Timestamp End;

    public Appointment(String location, Timestamp begin, Timestamp end, String physician) {
        Location = location;
        Begin = begin;
        End = end;
        Physician = physician;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Timestamp getBegin() {
        return Begin;
    }

    public void setBegin(Timestamp begin) {
        Begin = begin;
    }

    public Timestamp getEnd() {
        return End;
    }

    public void setEnd(Timestamp end) {
        End = end;
    }

    public String getPhysician() {
        return Physician;
    }

    public void setPhysician(String physician) {
        Physician = physician;
    }

    private String Physician;

}
