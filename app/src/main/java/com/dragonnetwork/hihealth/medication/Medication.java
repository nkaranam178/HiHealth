package com.dragonnetwork.hihealth.medication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.dragonnetwork.hihealth.MainActivity;

import java.util.ArrayList;

public class Medication extends AppCompatActivity {
    EditText et;
    Button AddMedicationButton;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);
        AddMedicationButton = (Button) findViewById(R.id.button_addData);
        lv = (ListView) findViewById(R.id.listView_lv);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Medication.this, android.R.layout.med_list_item, arrayList);
        lv.setAdapter(adapter);

        onBtnClick();
    }

    public void onBtnClick() {
        AddMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMedicationActivity.class);
                startActivity(intent);
                /*
                String result = et.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();
                */
            }
        });
    }
}
