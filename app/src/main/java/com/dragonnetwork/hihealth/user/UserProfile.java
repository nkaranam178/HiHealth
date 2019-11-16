package com.dragonnetwork.hihealth.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class UserProfile extends AppCompatActivity {

    private static TextView view_username;
    private static TextView view_email;
    private static TextView view_phone;

    private static FirebaseFirestore db;
    private static CollectionReference userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);

        db = FirebaseFirestore.getInstance();
        userDB = db.collection("Users");
        view_username = findViewById(R.id.username);
        view_email = findViewById(R.id.email);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userDB.document(userID).get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Map<String, Object> result;
                        result = task.getResult().getData();
                        final String user_name = result.get("Name").toString();
                        final String email = result.get("Email").toString();
                        view_username.setText(user_name);
                        view_email.setText(email);
                    }
                }
        );

//        view_phone = findViewById(R.id.phone);


        final Button edit_button = findViewById(R.id.profile_edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    /*public void signout(){
        User.signout();
    }*/
    /*public static void updateProfile(){
        view_username.setText(User.Username);
        view_email.setText(User.Email);
        view_phone.setText(User.Phone);
    }*/
}
