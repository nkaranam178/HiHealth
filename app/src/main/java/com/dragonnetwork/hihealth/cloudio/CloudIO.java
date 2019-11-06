package com.dragonnetwork.hihealth.cloudio;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.user.LoginActivity;
import com.dragonnetwork.hihealth.user.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
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


    public void initCloud(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        UserDB = db.collection("Users");
    }


    public static void Login(String email, String password, final LoginActivity context){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
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
                                            /*User.setFirst_Name(UserDoc.getString("First_Name"));
                                            User.setLast_Name(UserDoc.getString("Last_Name"));
                                            User.setLocation(UserDoc.getString("Location"));
                                            User.setSex(UserDoc.getBoolean("Sex"));*/
                                            User.setEmail(UserDoc.getString("Email"));
                                            User.setName(UserDoc.getString("Name"));
                                            User.setDateOfBirth(UserDoc.getString("DateOfBirth"));
                                            User.setAppointments((List<String>) UserDoc.get("Appointments"));
                                            User.setMedications((List<String>) UserDoc.get("Medications"));
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
                            /*User.setFirst_Name(first_name);
                            User.setLast_Name(last_name);
                            User.setLocation(location);
                            User.setSex(sex);*/
                            User.setName(name);
                            User.setDateOfBirth(dob);
                            User.setAppointments(new ArrayList<String>());
                            User.setMedications(new ArrayList<String>());
                            User.setReports(new ArrayList<String>());
                            User.setSymptoms(new ArrayList<Map>());
                            User.setStatus(true);

                            Map<String,Object> newuser = new HashMap<>();
                            newuser.put("Appointments", User.getAppointments());
                            newuser.put("Email", User.getEmail());
                            /*newuser.put("First_Name", User.getFirst_Name());
                            newuser.put("Last_Name", User.getLast_Name());
                            newuser.put("Location", User.getLocation());*/
                            newuser.put("Name",User.getName());
                            newuser.put("DateOfBirth", User.getDateOfBirth());
                            newuser.put("Medications", User.getMedications());
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


    public void SignOut() {
        mAuth.signOut();
    }


    public void RequestAppointmentDoc(String AptID) {

    }


    public void RequestMedicationDoc(String MedID) {

    }


    public void RequestReportDoc(String RepID) {

    }




}
