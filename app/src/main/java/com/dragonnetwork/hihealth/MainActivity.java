package com.dragonnetwork.hihealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.dragonnetwork.hihealth.cloudio.CloudIO;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.medication.MedicationActivity;
import com.dragonnetwork.hihealth.medication.MedicationAdaptor;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.reminders_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        CloudIO.initCloud();
        RecyclerView rv_morning = (RecyclerView) findViewById(R.id.rv_reminders);

        ArrayList<Medication> medications = createMedicationsList(6);
        MedicationAdaptor adapter = new MedicationAdaptor(medications);
        // Attach the adapter to the recyclerview to populate items
        rv_morning.setAdapter(adapter);
        // Set layout manager to position the items
        rv_morning.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        // TODO need to check if the activity selected is already in use
        switch (menuItem.getItemId()) {
            case R.id.nav_medications:
                intent = new Intent(this, MedicationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_reminders:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_reports:
                // must implement reports activity
                break;

            case R.id.nav_settings:
                // must implement settings activity
                break;
            case R.id.nav_symptoms:
                // must implement settings activity
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
