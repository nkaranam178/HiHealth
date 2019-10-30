package com.dragonnetwork.hihealth.medication;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dragonnetwork.hihealth.R;

public class MedicationHolder extends RecyclerView.ViewHolder {
    ImageView IconView;
    TextView MedicineView;
    TextView DosageView;
    TextView FrequencyView;
    public MedicationHolder(@NonNull View itemView) {
        super(itemView);
        this.IconView = itemView.findViewById(R.id.icon_view);
        this.MedicineView = itemView.findViewById(R.id.medicine_view);
        this.DosageView = itemView.findViewById(R.id.dosage_view);
        this.FrequencyView = itemView.findViewById(R.id.frequency_view);
    }
}
