package com.dragonnetwork.hihealth.medication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.cloudio.CloudIO;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.User;


public class AddMedicationActivity extends AppCompatActivity {
    EditText MedicationName;
    EditText MedicationType;
    EditText TotalPills;
    EditText Strength;
    EditText Dosage;
    EditText Frequency;
    EditText Icon;
    Button Addbt;
    final String TAG = "AddMedicationActivity";
    ProgressDialog progressDialog;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        MedicationName = findViewById(R.id.medication_name_editText);
        MedicationType = findViewById(R.id.medication_type_editText);
        TotalPills = findViewById(R.id.total_pills_editText);
        Strength = findViewById(R.id.medication_strength_editText);
        Dosage = findViewById(R.id.medication_dosage_editText);
        Frequency = findViewById(R.id.medication_frequency_editText);
        //Icon = findViewById(R.id.medication_icon_selection);
        Addbt = findViewById(R.id.Addbt);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        initProgressDialog();

        //disable button and show progress dialog
        Addbt.setEnabled(true);

        onBtnClick();
    }
        public void onBtnClick() {

            Addbt.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                progressDialog.show();
                Addbt.setEnabled(false);
                    if (validateForm()) {
                        User.addMedication(MedicationName.getText().toString(),
                                MedicationType.getText().toString(),
                                Integer.parseInt(TotalPills.getText().toString()),
                                Strength.getText().toString(),
                                Integer.parseInt(Dosage.getText().toString()),
                                Frequency.getText().toString());
                        progressDialog.dismiss();
                        Addbt.setEnabled(true);
                        Intent intent = new Intent(getApplicationContext(), MedicationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        progressDialog.dismiss();
                        Addbt.setEnabled(true);
                    }

                }
            });
        }

    public void initProgressDialog(){
        progressDialog = new ProgressDialog(AddMedicationActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding Medication...");
    }

    private boolean validateForm() {
        //TODO: Implement this method according to our design.
        boolean valid = true;
        //Check Name, type, frequency, dosage, strength, total number

        if(TextUtils.isEmpty(MedicationName.getText().toString()))
        {
            MedicationName.setError("Required");
            valid = false;
        }
        else{
            MedicationName.setError(null);
        }

        if(TextUtils.isEmpty(MedicationType.getText().toString()))
        {
            MedicationType.setError("Required");
            valid = false;
        }
        else{
            MedicationType.setError(null);
        }

        if(TextUtils.isEmpty(TotalPills.getText().toString()))
        {
            TotalPills.setError("Required");
            valid = false;
        }
        else{
            TotalPills.setError(null);
        }

        if(TextUtils.isEmpty(Strength.getText().toString()))
        {
            Strength.setError("Required");
            valid = false;
        }
        else{
            Strength.setError(null);
        }

        if(TextUtils.isEmpty(Dosage.getText().toString()))
        {
            Dosage.setError("Required");
            valid = false;
        }
        else{
            Dosage.setError(null);
        }

        if(TextUtils.isEmpty(Frequency.getText().toString()))
        {
            Frequency.setError("Required");
            valid = false;
        }
        else{
            Frequency.setError(null);
        }

     // w   Log.w(TAG,"valid = " + valid);
        return valid;
    }

    }
