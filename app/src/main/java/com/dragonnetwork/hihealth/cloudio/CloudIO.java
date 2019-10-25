package com.dragonnetwork.hihealth.cloudio;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dragonnetwork.hihealth.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class CloudIO {
    private static FirebaseAuth mAuth;
    private static final String TAG = "CloudUI";
    private static FirebaseFirestore db;
    private static CollectionReference UserDB;
    private static CollectionReference AppointmentsDB;
    //ProgressDialog progressDialog;

    public void initCloud(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        UserDB = db.collection("Users");

    }
    public void Login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
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
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot UserDoc = task.getResult();
                                        if (UserDoc.exists()) {
                                            Log.d(TAG, "DocumentSnapshot data: " + UserDoc.getData());
                                            User.setFirst_Name(UserDoc.getString("First_Name"));
                                            User.setLast_Name(UserDoc.getString("Last_Name"));
                                            User.setLocation(UserDoc.getString("Location"));
                                            User.setSex(UserDoc.getBoolean("Sex"));
                                            User.setAppointments((List<String>)UserDoc.get("Appointments"));
                                            User.setMedications((List<String>)UserDoc.get("Medications"));
                                            User.setReports((List<String>)UserDoc.get("Reports"));
                                            User.setSymptoms((List<Map>)UserDoc.get("Symptoms"));
                                            User.setStatus(true); // Marked User is logged in.
                                            // Complete login procedure.
                                        } else {
                                            Log.d(TAG, "Document does not exist in User collection");
                                        }
                                    } else {
                                        Log.d(TAG, "Fail to get user document:", task.getException());
                                    }
                                }
                            });
                            Log.d(TAG, "GetUserProfile:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            // TODO: Complete action if user authentication failed. Stay in Login Activity.
                        }
                    }
                });
    }

}
