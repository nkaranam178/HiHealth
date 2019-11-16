package com.dragonnetwork.hihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

public class ReportsActivity extends AppCompatActivity {
    String messageToSend = "Emergency Text";
    String number = "6086220769";
    SmsManager sm = SmsManager.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        findViewById(R.id.callPhysician).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("6086220769");
            }
        });

//        findViewById(R.id.textPhysician).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sm.sendTextMessage(number, null, messageToSend, null,null);
//            }
//        });

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }


}
