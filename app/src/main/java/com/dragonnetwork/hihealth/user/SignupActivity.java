package com.dragonnetwork.hihealth.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.R;





import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    ProgressDialog progressDialog = null;
    @BindView(R.id.register_name) EditText _nameText;
    @BindView(R.id.register_email) EditText _emailText;
    @BindView(R.id.register_phone) EditText _mobileText;
    @BindView(R.id.register_password) EditText _passwordText;
    @BindView(R.id.register_passwordreenter) EditText _reEnterPasswordText;
    @BindView(R.id.register_button) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    /*
        Initialize Progress Dialog, the red pop up view shows system is working.
     */
    public void initProgressDialog(){
        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        // TODO: Update the signup Button
        _signupButton.setOnClickListener(v -> {
            if(validate())
                signup();

        });

        // TODO: Implement the link that navigate to LoginActivity
        _loginLink.setOnClickListener(v -> {
            // Finish the registration screen and return to the Login activity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        });

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

    public void signup() {
        Log.d(TAG, "Signup");
        _signupButton.setEnabled(false); // Disable the button
        progressDialog.show(); // Show progress Dialog
        if (!validate()) onSignupFailed();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String name = _nameText.getText().toString();
        String mobile = _mobileText.getText().toString();
        // TODO: Call CloudIO.signup()


        /*
            An OS handler that check User status but I am not using it right now.
         */
        /*new android.os.Handler().postDelayed(
                () -> {
                    // On complete call either onSignupSuccess or onSignupFailed
                    // depending on success
                    if(User.isStatus()){
                        onSignupSuccess();
                    }
                    else{
                        onSignupFailed();
                    }
                    //progressDialog.dismiss();
                }, 3000);*/
    }


    public void onSignupSuccess() {
        progressDialog.dismiss(); // Hide the Progress Dialog
        _signupButton.setEnabled(true); // Re-enable the button
        setResult(RESULT_OK, null);
        // Navigate to Main Activity
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        progressDialog.dismiss(); // Hide the Progress Dialog
        _signupButton.setEnabled(true); // Re-enable the button
    }
    public boolean validate() {
        // TODO: Update this function base on our design
        boolean valid = true;

        String name = _nameText.getText().toString();
        //String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

}