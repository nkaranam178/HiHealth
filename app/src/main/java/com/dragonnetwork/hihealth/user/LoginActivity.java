package com.dragonnetwork.hihealth.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.cloudio.CloudIO;
import com.dragonnetwork.hihealth.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    //private LoginViewModel loginViewModel;
    private FirebaseAuth mAuth;
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "EmailPassword";
    FirebaseFirestore db; //Fire Store Instance
    ProgressDialog progressDialog;

    @BindView(R.id.login_email) EditText emailEditText;
    @BindView(R.id.login_password) EditText passwordEditText;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.link_signup) TextView RegisterLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        initProgressDialog();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm())
                    signIn();
            }
        });
        RegisterLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);

                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());



        // Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initProgressDialog(){
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging In...");
    }
    @Override
    public void onStart() {
        super.onStart();
    }


    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        //User.Email = emailEditText.getText().toString();
        setResult(RESULT_OK, null);
        progressDialog.dismiss();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
        progressDialog.dismiss();
    }
    private void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        progressDialog.show();
        loginButton.setEnabled(false);

        if(!validateForm()) onLoginFailed();
        Log.d(TAG, "signIn:" + email);
        CloudIO.Login(email, password, this);
        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                            if(User.isStatus())
                                onLoginSuccess();
                            else
                                onLoginFailed();
                    }
                }, 3000);*/
    }
    private boolean validateForm() {
        //TODO: Implement this method according to our design.
        boolean valid = true;

        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        return valid;
    }

}
