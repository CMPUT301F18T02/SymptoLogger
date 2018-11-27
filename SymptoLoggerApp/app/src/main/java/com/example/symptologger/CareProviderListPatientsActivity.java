package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CareProviderListPatientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_list_patients);

        FloatingActionButton logOutFAB = findViewById(R.id.addPatientFAB);
        logOutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CareProviderListPatientsActivity.this,"Add Patient", Toast.LENGTH_SHORT).show();
                Intent addPatientIntent = new Intent(CareProviderListPatientsActivity.this,AddPatientActivity.class);
                startActivity(addPatientIntent);
            }
        });
    }
}
