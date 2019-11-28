package com.dragonnetwork.hihealth.data;

import com.dragonnetwork.hihealth.cloudio.CloudIO;

import java.util.List;
import java.util.Map;

public class User {
    private static boolean status; // True: User is signed in. False: User is not signed in. Set this to false during initialize.
    private static String UID;
    private static String Email;
    private static String Name;
    private static String DateOfBirth;
    private static List<Appointment> Appointments;
    private static List<String> AppointmentIDs;
    private static List<Medication> Medications;
    private static List<String> MedicationIDs;
    private static List<String> Reports;
    private static String Symptoms;

    public static List<String> getAppointmentIDs() {
        return AppointmentIDs;
    }

    public static void setAppointmentIDs(List<String> appointmentIDs) {
        AppointmentIDs = appointmentIDs;
    }

    public static List<String> getMedicationIDs() {
        return MedicationIDs;
    }

    public static void setMedicationIDs(List<String> medicationIDs) {
        MedicationIDs = medicationIDs;
    }

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

    public static List<Appointment> getAppointments() {
        return Appointments;
    }

    public static void setAppointments(List<Appointment> appointments) {
        Appointments = appointments;
    }

    public static List<Medication> getMedications() {
        return Medications;
    }

    public static void setMedications(List<Medication> medications) {
        Medications = medications;
    }

    public static List<String> getReports() {
        return Reports;
    }

    public static void setReports(List<String> reports) {
        Reports = reports;
    }

    public static String  getSymptoms() {
        return Symptoms;
    }

    public static void setSymptoms(String symptoms) {
        Symptoms = symptoms;
        CloudIO.UpdateSymptom(Symptoms);
    }

    public static void SignOut(){
        UID = "";
        Email = "";
        Name = "";
        DateOfBirth = "";
        Appointments = null;
        MedicationIDs = null;
        MedicationIDs = null;
        Reports = null;
        Symptoms = null;
        CloudIO.SignOut();
    }

    public static void addMedication(String prescription, String type, int totalnum, String strength, int doses, String frequency){
        CloudIO.addMedication(prescription, type, totalnum, strength, doses, frequency);
    }

    public static void addMedication(Medication medication){
        Medications.add(medication);
        MedicationIDs.add(medication.getMedID());
    }


}
