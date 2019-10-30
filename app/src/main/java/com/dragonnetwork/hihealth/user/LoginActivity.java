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

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "EmailPassword";
    ProgressDialog progressDialog;

    @BindView(R.id.login_email) EditText emailEditText;
    @BindView(R.id.login_password) EditText passwordEditText;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.link_signup) TextView RegisterLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initProgressDialog();
        loginButton.setOnClickListener(v -> {
            if(validateForm())
                signIn();
        });
        //TODO: Implement the link that navigate to register activity
        RegisterLink.setOnClickListener(v -> {
            // Start the Signup activity
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);

            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
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
        setResult(RESULT_OK, null);
        progressDialog.dismiss();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public void onLoginFailed() {
        // TODO: Iteration 2 or 3: Toast custom text by passing in a variable.
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
        loginButton.setEnabled(true);
    }
    private void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Log.d(TAG, "signIn:" + email);

        // Setup Progress Dialog
        loginButton.setEnabled(false);
        progressDialog.show();

        if(!validateForm()) onLoginFailed();
        CloudIO.Login(email, password,this);
        /*new android.os.Handler().postDelayed(
                () -> {
                        if(User.isStatus())
                            onLoginSuccess();
                        else
                            onLoginFailed();
                }, 3000);*/
    }
    private boolean validateForm() {
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
