package com.dragonnetwork.hihealth.medication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dragonnetwork.hihealth.CalendarActivity;
import com.dragonnetwork.hihealth.MainActivity;
import com.dragonnetwork.hihealth.ReportsActivity;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.medication.AddMedicationActivity;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.user.UserProfile;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MedicationActivity extends MainActivity implements NavigationView.OnNavigationItemSelectedListener{
    final String TAG = "MedicationActivity";
    EditText et;
    Button AddMedicationButton;
    RecyclerView lv;
    List<Medication> medicationList;

    protected DrawerLayout drawer;
//    ArrayAdapter<String> adapter;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentView = R.id.nav_medications;
        setContentView(R.layout.activity_medication);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.medications_layout);
        onCreateDrawer();

        View header = navigationView.getHeaderView(0);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MedicationActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MedicationActivity.this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        lv =  findViewById(R.id.medications_rv);
        LinearLayoutManager layout = new LinearLayoutManager(this);
     //   lv.setHasFixedSize(true);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        switch (menuItem.getItemId()) {
            case R.id.nav_medications:
                drawer.closeDrawer(Gravity.LEFT);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_reminders:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_calendar:
                intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_reports:
                intent = new Intent(this, ReportsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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

    public void onBtnClick() {
        AddMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMedicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
