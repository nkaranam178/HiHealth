package com.dragonnetwork.hihealth.medication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.medication.AddMedicationActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationActivity extends AppCompatActivity {
    final String TAG = "MedicationActivity";
    EditText et;
    Button AddMedicationButton;
    RecyclerView lv;
    List<Medication> medicationList;
//    ArrayAdapter<String> adapter;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        lv =  findViewById(R.id.medications_rv);
        LinearLayoutManager layout = new LinearLayoutManager(this);
     //   lv.setHasFixedSize(true);
        setSupportActionBar(toolbar);

//        et = (EditText) findViewById(R.id.editText);
        AddMedicationButton = findViewById(R.id.button_add_medication);
//        lv = (ListView) findViewById(R.id.medications_rv);

        medicationList = User.getMedications();
        //medicationList.add(new Medication("ID","prescription","type",100,"strength",3
          //                                  ,"freq"));

        Log.w(TAG,"Medications" + User.getMedications().size());
        MedicationAdaptor adapter = new MedicationAdaptor(User.getMedications());
        lv.setLayoutManager(layout);
        lv.setAdapter(adapter);



        onBtnClick();
    }

    public void onBtnClick() {
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String result = et.getText().toString();
//                arrayList.add(result);
//                adapter.notifyDataSetChanged();
//            }
//        });

        AddMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMedicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
