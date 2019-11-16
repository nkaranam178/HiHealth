package com.dragonnetwork.hihealth.medication;

import android.os.Bundle;
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

import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.cloudio.CloudIO;


public class AddMedicationActivity extends AppCompatActivity {
    EditText MedicationName;
    EditText MedicationType;
    EditText TotalPills;
    EditText Strength;
    EditText Dosage;
    EditText Frequency;
    EditText Icon;
    Button Addbt;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        MedicationName = (EditText) findViewById(R.id.medication_name_editText);
        MedicationType = (EditText) findViewById(R.id.medication_type_editText);
        TotalPills = (EditText) findViewById(R.id.total_pills_editText);
        Strength = (EditText) findViewById(R.id.medication_strength_editText);
        Dosage = (EditText) findViewById(R.id.medication_dosage_editText);
        Frequency = (EditText) findViewById(R.id.medication_frequency_editText);
        Icon = (EditText) findViewById(R.id.medication_icon_selection);

        Addbt = (Button) findViewById(R.id.button_add_medication);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        onBtnClick();
    }
        public void onBtnClick() {
            Addbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CloudIO.addMedication(MedicationName.getText().toString(),
                                          MedicationType.getText().toString(),
                                          Integer.parseInt(TotalPills.getText().toString()),
                                          Strength.getText().toString(),
                                          Integer.parseInt(Dosage.getText().toString()),
                                          Frequency.getText().toString());
                }
            });
        }
    }
