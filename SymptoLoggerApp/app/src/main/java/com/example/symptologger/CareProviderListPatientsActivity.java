package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CareProviderListPatientsActivity extends AppCompatActivity {

    ListView patientListView;
    ArrayList<String> patientUserNamesList;
    ArrayAdapter<String> patientListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_list_patients);



        FloatingActionButton addPatientFAB = findViewById(R.id.addPatientFAB);
        addPatientFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CareProviderListPatientsActivity.this,"Add Patient", Toast.LENGTH_SHORT).show();
                Intent addPatientIntent = new Intent(CareProviderListPatientsActivity.this,AddPatientActivity.class);
                startActivity(addPatientIntent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        patientListView = (ListView) findViewById(R.id.patientListView);
        patientUserNamesList = new ArrayList<String>();
        patientListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, patientUserNamesList);
        patientListView.setAdapter(patientListAdapter);
    }
}
