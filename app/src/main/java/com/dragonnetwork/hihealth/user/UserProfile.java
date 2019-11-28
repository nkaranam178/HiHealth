package com.dragonnetwork.hihealth.user;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.Map;

public class UserProfile extends AppCompatActivity {

    private static TextView view_username;
    private static EditText et_email;
    private static EditText view_phone;
    private static EditText view_docname;
    private static EditText view_docemail;
    private static EditText view_docphone;

    private static String userFullName;
    private static String userEmail;
    private static String userPhone;

    private static FirebaseFirestore db;
    private static CollectionReference userDB;
    private static DocumentReference userDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);

        db = FirebaseFirestore.getInstance();
        userDB = db.collection("Users");
        view_username = findViewById(R.id.username);
        et_email = findViewById(R.id.email);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userDoc = userDB.document(userID);

        userDoc.get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Map<String, Object> result;
                        result = task.getResult().getData();
                        final String user_name = result.get("Name").toString();
                        userFullName = user_name;
                        final String email = result.get("Email").toString();
                        userEmail = email;
                        view_username.setText(user_name);
                        et_email.setText(email);
                    }
                }
        );
        //Add back button
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });*/

//        view_phone = findViewById(R.id.phone);

        final Button edit_button = findViewById(R.id.profile_edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onEditButtonPressed()) {
                    // successful
                } else {
                    // failure occurred - handle it or something?...
                }
            }
        });
    }

    private boolean onEditButtonPressed() {
        AlertDialog.Builder builder;

        // only make changes to database if user edited the fields. Otherwise ignore button press
        // make sure to check if other edit texts are altered when they are implemented
//        if (!userFullName.equals(view_username.getText().toString())) {
            // update the data in firebase
            final String email = et_email.getText().toString();
            builder = new AlertDialog.Builder(this).
            setTitle("Confirm Profile Edit").
            setMessage("Save your changes?").
            setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.runTransaction(new Transaction.Function<Void>() {
                        @Override
                        public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                            transaction.update(userDoc, "Email", email);
                            return null;
                        }
                    });
                    Toast.makeText(
                            UserProfile.this,
                            "Changes Saved.",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }).
            setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(
                            UserProfile.this,
                            "Cancelled changes.",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
            builder.show();
//        }
        return true;  // TODO check for failure, if failed return false
    }
}
