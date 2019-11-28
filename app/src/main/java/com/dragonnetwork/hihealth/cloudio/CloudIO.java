package com.dragonnetwork.hihealth.cloudio;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dragonnetwork.hihealth.data.Appointment;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.user.LoginActivity;
import com.dragonnetwork.hihealth.user.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudIO {
    private static FirebaseAuth mAuth;
    private static final String TAG = "CloudUI";
    private static FirebaseFirestore db;
    private static CollectionReference UserDB;
    private static CollectionReference AppointmentsDB;
    private static CollectionReference MedicationsDB;


    public static void initCloud(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        UserDB = db.collection("Users");
        AppointmentsDB = db.collection("Appointments");
        MedicationsDB = db.collection("Medications");
        Log.w(TAG,"Cloud initialize Success.");
    }

    public static List<Medication> getMedications(List<String> medIDs){
        final List<Medication> medications = new ArrayList();
        DocumentReference MedDocRef;
        if(medIDs.size()!=0)
            for(String medID : medIDs){
                MedDocRef = MedicationsDB.document(medID);
                MedDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot MedDoc = task.getResult();
                            if(MedDoc.exists()){
                                Log.d(TAG,"Medication Document Snapshot data: " + MedDoc.getData());
                                medications.add(new Medication(MedDoc.getId(),MedDoc.getString("Prescription"), MedDoc.getString("Type"), MedDoc.getDouble("TotalNum").intValue(),
                                        MedDoc.getString("Strength"), MedDoc.getDouble("Doses").intValue(), MedDoc.getString("Frequency")));
                            }
                        }
                    }
                });
            }
        return medications;
    }

    public static List<Appointment> getAppointments(List<String> AppIDs){
        final List<Appointment> appointments = new ArrayList<>();
        DocumentReference AppRef;
        if(AppIDs.size()!=0)
            for(String medID : AppIDs){
                AppRef = AppointmentsDB.document(medID);
                AppRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot AppDoc = task.getResult();
                            if(AppDoc.exists()){
                                Log.d(TAG,"Medication Document Snapshot data: " + AppDoc.getData());
                                appointments.add(new Appointment(AppDoc.getId(), AppDoc.getString("Location"),AppDoc.getTimestamp("Begin"), AppDoc.getTimestamp("End"), AppDoc.getString("Physician")));
                            }
                        }
                    }
                });
            }
        return appointments;
    }
    public static void addMedication(final String prescription, final String type, final int totalNum, final String strength, final int doses, final String frequency){
        Map<String, Object> medication = new HashMap<>();
        medication.put("Prescription", prescription);
        medication.put("Type",type);
        medication.put("TotalNum",totalNum);
        medication.put("Strength", strength);
        medication.put("Doses", doses);
        medication.put("Frequency", frequency);
        MedicationsDB.add(medication).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "CloudIO add new medication with ID: " + documentReference.getId());
                User.addMedication(new Medication(documentReference.getId(), prescription, type, totalNum, strength, doses, frequency));
                UserDB.document(User.getUID()).update("MedicationIDs", User.getMedicationIDs());
            }
        });

    }
    public static void Login(String email, String password, final LoginActivity context){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String uid = mAuth.getUid();
                            User.setUID(uid);
                            User.setEmail(currentUser.getEmail());
                            DocumentReference UserdocRef = UserDB.document(uid);
                            UserdocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task1) {
                                    if (task1.isSuccessful()) {
                                        DocumentSnapshot UserDoc = task1.getResult();
                                        if (UserDoc.exists()) {
                                            Log.d(TAG, "DocumentSnapshot data: " + UserDoc.getData());

                                            User.setEmail(UserDoc.getString("Email"));
                                            User.setName(UserDoc.getString("Name"));
                                            User.setDateOfBirth(UserDoc.getString("DateOfBirth"));
                                            List<String> appIDs = (List<String>) UserDoc.get("AppointmentIDs");
                                            User.setAppointmentIDs(appIDs);
                                            User.setAppointments(getAppointments(appIDs));
                                            List<String> medIDs = (List<String>) UserDoc.get("MedicationIDs");
                                            User.setMedicationIDs((medIDs));
                                            User.setMedications(getMedications(medIDs));
                                            User.setReports((List<String>) UserDoc.get("Reports"));
                                            User.setSymptoms((List<Map>) UserDoc.get("Symptoms"));
                                            User.setStatus(true); // Marked User is logged in.
                                            // Complete login procedure.
                                            Log.d(TAG, "Login Success.");
                                            context.onLoginSuccess();
                                        } else {
                                            Log.d(TAG, "Document does not exist in User collection");
                                            context.onLoginFailed();
                                        }
                                    } else {
                                        Log.d(TAG, "Fail to get user document:", task1.getException());
                                        context.onLoginFailed();
                                    }
                                }
                            });
                            //Log.d(TAG, "GetUserProfile:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            // TODO: Complete action if user authentication failed. Stay in Login Activity.
                            context.onLoginFailed();
                        }
                    }
                });
    }

    /*
        SignUp function.
        This function receive user info from register activity and create a user account to Firebase authentication and a new document in the Firestore Users collection.
        The document ID is the user ID generated by Firebase Authentication.
     */

    public static void SignUp(final String email, String password, final String name, final String dob, final SignupActivity context){
        Log.d(TAG,"CreateAccount:"+email+":"+password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            User.setUID(mAuth.getUid());
                            User.setEmail(email);
                            User.setName(name);
                            User.setDateOfBirth(dob);
                            User.setAppointmentIDs(new ArrayList<String>());
                            User.setMedications(new ArrayList<Medication>());
                            User.setReports(new ArrayList<String>());
                            User.setSymptoms(new ArrayList<Map>());
                            User.setStatus(true);
                            Map<String,Object> newuser = new HashMap<>();
                            newuser.put("Appointments", User.getAppointments());
                            newuser.put("Email", User.getEmail());
                            newuser.put("Name",User.getName());
                            newuser.put("DateOfBirth", User.getDateOfBirth());
                            newuser.put("MedicationIDs", User.getMedications());
                            newuser.put("Reports", User.getReports());
                            //newuser.put("Sex", User.isSex());
                            newuser.put("Symptoms", User.getSymptoms());
                            UserDB.document(User.getUID()).set(newuser);
                            User.setStatus(true);
                            // End of cloud user register procedure.
                            context.onSignupSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            User.setStatus(false);
                            context.onSignupFailed();
                        }

                    }
                });
    }


    public static void SignOut() {
        mAuth.signOut();
    }





}
