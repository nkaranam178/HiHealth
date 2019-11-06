package com.dragonnetwork.hihealth.data;

import java.util.List;
import java.util.Map;

public class User {
    private static boolean status; // True: User is signed in. False: User is not signed in. Set this to false during initialize.
    private static String UID;
    private static String Email;
    private static String Name;
    //private static String Location;
    //private static boolean Sex;
    private static String DateOfBirth;
    private static List<String> Appointments;
    private static List<String> Medications;
    private static List<String> Reports;
    private static List<Map> Symptoms;

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        User.status = status;
    }

    public static String getUID() {
        return UID;
    }

    public static void setUID(String UID) {
        User.UID = UID;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        Email = email;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static String getDateOfBirth() {
        return DateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    /*public static String getLocation() {
        return Location;
    }

    public static void setLocation(String location) {
        Location = location;
    }*/

    /*public static boolean isSex() {
        return Sex;
    }

    public static void setSex(boolean sex) {
        Sex = sex;
    }*/

    public static List<String> getAppointments() {
        return Appointments;
    }

    public static void setAppointments(List<String> appointments) {
        Appointments = appointments;
    }

    public static List<String> getMedications() {
        return Medications;
    }

    public static void setMedications(List<String> medications) {
        Medications = medications;
    }

    public static List<String> getReports() {
        return Reports;
    }

    public static void setReports(List<String> reports) {
        Reports = reports;
    }

    public static List<Map> getSymptoms() {
        return Symptoms;
    }

    public static void setSymptoms(List<Map> symptoms) {
        Symptoms = symptoms;
    }



}
