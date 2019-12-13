package com.dragonnetwork.hihealth.reminder;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.data.Medication;
import com.dragonnetwork.hihealth.data.Reminder;
import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.medication.MedicationAdaptor;

import java.util.List;


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ReminderAdaptor extends RecyclerView.Adapter<com.dragonnetwork.hihealth.reminder.ReminderAdaptor.ViewHolder> {

    private List<Reminder> reminders;

    // Pass in the contact array into the constructor
    public ReminderAdaptor(List<Reminder> contacts) {
        reminders = contacts;
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView remInfo;
        public TextView remInstructions;
        public ImageView icon;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            remInfo = (TextView) itemView.findViewById(R.id.reminder_info);
            remInstructions = (TextView) itemView.findViewById(R.id.reminder_instructions);
            icon = (ImageView) itemView.findViewById(R.id.reminder_icon);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    @NonNull
    public com.dragonnetwork.hihealth.reminder.ReminderAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View medView = inflater.inflate(R.layout.card_reminder, parent, false);

        // Return a new holder instance
        com.dragonnetwork.hihealth.reminder.ReminderAdaptor.ViewHolder viewHolder = new com.dragonnetwork.hihealth.reminder.ReminderAdaptor.ViewHolder(medView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Reminder reminder = reminders.get(position);
        User.fetchReminders();
        // Set item views based on your views and data model
        TextView textView = holder.remInfo;
        textView.setText(reminder.getInfo() + " - " + reminder.getInstructions());
        TextView remInfo = holder.remInstructions;
      //  String medStatus = "" + reminder.getTotalNum();
//        medStatus += " pills " + med.isMedSkipped() ? "skipped" : "taken";
   //     medStatus += " pills taken";
   //     medStatus += " at " + med.getFrequency();
   //     medInfo.setText(medStatus);

        /*
        ImageView icon = holder.icon;
        switch(med.getIconType()) {
            case (R.id.syringe_button):
                icon.setImageResource(R.drawable.syringe);
                break;
            case(R.id.inhaler_button):
                icon.setImageResource(R.drawable.inhaler);
                break;
            default:
                icon.setImageResource(R.drawable.pills);
        }
         */
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return reminders.size();
    }
}