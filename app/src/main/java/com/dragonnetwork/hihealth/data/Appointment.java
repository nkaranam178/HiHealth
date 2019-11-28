package com.dragonnetwork.hihealth.data;

import com.google.firebase.Timestamp;

//Timestamp Document: https://firebase.google.com/docs/reference/android/com/google/firebase/Timestamp
public class Appointment {
    private String AppID;
    private String Location;
    private Timestamp Begin;
    private Timestamp End;
    private String Physician;

    public Appointment(String appID, String location, Timestamp begin, Timestamp end, String physician) {
        AppID = appID;
        Location = location;
        Begin = begin;
        End = end;
        Physician = physician;
    }

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String appID) {
        AppID = appID;
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
}
