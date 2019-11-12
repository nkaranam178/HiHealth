package com.dragonnetwork.hihealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dragonnetwork.hihealth.cloudio.CloudIO;
import com.dragonnetwork.hihealth.data.Medication;

import java.sql.Time;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching the layout to the toolbar object
//        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
//        setSupportActionBar(toolbar);
        CloudIO.initCloud();
        RecyclerView rv_morning = (RecyclerView) findViewById(R.id.morning_rv);

        ArrayList<Medication> medications = createMedicationsList(6);
        MedicationAdaptor adapter = new MedicationAdaptor(medications);
        // Attach the adapter to the recyclerview to populate items
        rv_morning.setAdapter(adapter);
        // Set layout manager to position the items
        rv_morning.setLayoutManager(new LinearLayoutManager(this));

        // do this again for the other recycler view (afternoon and evening)
        medications = createMedicationsList(3);
        MedicationAdaptor adapter2 = new MedicationAdaptor(medications);
        RecyclerView rv_afternoon = (RecyclerView) findViewById(R.id.afternoon_rv);
        rv_afternoon.setAdapter(adapter2);
        rv_afternoon.setLayoutManager(new LinearLayoutManager(this));

        medications = createMedicationsList(9);
        MedicationAdaptor adapter3 = new MedicationAdaptor(medications);
        RecyclerView rv_evening = (RecyclerView) findViewById(R.id.evening_rv);
        rv_evening.setAdapter(adapter3);
        rv_evening.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Function just for testing... not useful in practice
     * @param numMeds
     * @return
     */
    ArrayList<Medication> createMedicationsList(int numMeds) {
        ArrayList<Medication> list = new ArrayList<>();
        for (int i=0; i<numMeds; i++) {
            //Medication med = new Medication("Med" + i, i, i + " units", new Time(i, i, i));
            //list.add(med);
        }
        return list;
    }
}
