package com.dragonnetwork.hihealth.medication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.Medication;

import java.util.List;


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MedicationAdaptor extends
        RecyclerView.Adapter<MedicationAdaptor.ViewHolder> {

    private List<Medication> medications;

    // Pass in the contact array into the constructor
    public MedicationAdaptor(List<Medication> contacts) {
        medications = contacts;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView medInfo;
        public TextView medInstructions;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            medInfo = (TextView) itemView.findViewById(R.id.medication_info);
            medInstructions = (TextView) itemView.findViewById(R.id.medication_instructions);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    @NonNull
    public MedicationAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View medView = inflater.inflate(R.layout.card_reminder, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(medView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MedicationAdaptor.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Medication med = medications.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.medInfo;
        textView.setText(med.getMedName() + " - " + med.getDosage());
        TextView medInfo = viewHolder.medInstructions;
        String medStatus = "" + med.getNumPills();
//        medStatus += " pills " + med.isMedSkipped() ? "skipped" : "taken";
        medStatus += " pills taken";
        medStatus += " at " + med.getTime();
        medInfo.setText(medStatus);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return medications.size();
    }
}