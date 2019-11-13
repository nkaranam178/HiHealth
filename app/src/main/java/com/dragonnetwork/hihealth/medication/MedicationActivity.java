package com.dragonnetwork.hihealth.medication;

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
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.medication.AddMedicationActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.Medication;

import java.util.ArrayList;

public class MedicationActivity extends AppCompatActivity {
    EditText et;
    Button bt;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

//        et = (EditText) findViewById(R.id.editText);
        bt = (Button) findViewById(R.id.button_add_medication);
//        lv = (ListView) findViewById(R.id.medications_rv);

        arrayList = new ArrayList<String>();
//        adapter = new ArrayAdapter<String>(Medication.this, android.R.layout.med_list_item, arrayList);
//        lv.setAdapter(adapter);

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

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicationActivity.this, AddMedicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
