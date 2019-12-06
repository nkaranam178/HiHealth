package com.dragonnetwork.hihealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonnetwork.hihealth.cloudio.CloudIO;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.medication.MedicationActivity;
import com.dragonnetwork.hihealth.medication.MedicationAdaptor;
import com.dragonnetwork.hihealth.user.UserProfile;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    protected DrawerLayout drawer;
    protected ActionBarDrawerToggle drawerToggle;
    protected NavigationView navigationView;

    protected int contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = R.id.nav_reminders;
        setContentView(R.layout.activity_main);
//        LayoutInflater inflater = (LayoutInflater) this
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View contentView = inflater.inflate(R.layout.activity_main, null, false);
//        drawer.addView(contentView);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.reminders_layout);
        onCreateDrawer();

        View header = navigationView.getHeaderView(0);
        TextView navUsername = (TextView)header.findViewById(R.id.username);
        navUsername.setText(User.getName());
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        CloudIO.initCloud();
        RecyclerView rv_morning = (RecyclerView) findViewById(R.id.rv_reminders);

        ArrayList<Medication> medications = createMedicationsList(6);
        MedicationAdaptor adapter = new MedicationAdaptor(medications);
        // Attach the adapter to the recyclerview to populate items
        rv_morning.setAdapter(adapter);
        // Set layout manager to position the items
        rv_morning.setLayoutManager(new LinearLayoutManager(this));
    }

    protected void onCreateDrawer() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        switch (menuItem.getItemId()) {
            case R.id.nav_medications:
                intent = new Intent(this, MedicationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_reminders:
                drawer.closeDrawer(Gravity.LEFT);
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
                intent = new Intent(this, SymptomsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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
