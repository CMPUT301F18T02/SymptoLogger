package com.example.symptologger;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * This is the welcome screen for care providers. After logging in to the app, they will see all of
 * the patients they are assigned to. They will be able to view profiles and delete patients from
 * their list.
 *
 * @author Patrick Tamm
 */
public class CareProviderListPatientsActivity extends AppCompatActivity {

    private String cpUserName;

    ListView patientListView;
    ArrayList<Patient> patientUserNamesList;
    ArrayAdapter<Patient> patientListAdapter;
    Collection<Patient> patients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_list_patients);

        Intent intent = getIntent();
        cpUserName = intent.getStringExtra("cpUserName");

        TextView cpText = findViewById(R.id.careProviderWelcomeText);
        cpText.setText("Welcome "+cpUserName);

        FloatingActionButton addPatientFAB = findViewById(R.id.addPatientFAB);
        addPatientFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CareProviderListPatientsActivity.this,"Add Patient", Toast.LENGTH_SHORT).show();
                Intent addPatientIntent = new Intent(CareProviderListPatientsActivity.this,AddPatientActivity.class);
                addPatientIntent.putExtra("cpUserName",cpUserName);
                startActivity(addPatientIntent);
            }
        });

        FloatingActionButton cpLogoutFAB = findViewById(R.id.cpLogoutFAB);
        cpLogoutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CareProviderListPatientsActivity.this,"Goodbye", Toast.LENGTH_SHORT).show();
                Intent logOutIntent = new Intent(CareProviderListPatientsActivity.this,SignInActivity.class);
                startActivity(logOutIntent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        patientListView = (ListView) findViewById(R.id.patientListView);
        patients = PatientListController.getPatientList(cpUserName).getPatients();
        patientUserNamesList = new ArrayList<Patient>(patients);
        patientListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, patientUserNamesList);
        patientListView.setAdapter(patientListAdapter);

        patientListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
                final int pos = position;

                AlertDialog.Builder modifyAlert = new AlertDialog.Builder(CareProviderListPatientsActivity.this);

                modifyAlert.setTitle(patientUserNamesList.get(pos).getUserID());
                modifyAlert.setCancelable(true);

                CharSequence[] options = {"View",
                        "Delete",
                        "Cancel"};

                modifyAlert.setItems(options, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        if (which == 0){
                            Intent viewIntent = new Intent(CareProviderListPatientsActivity.this, CareProviderViewPatientConcernsActivity.class);
                            Bundle viewBundle = new Bundle();
                            viewBundle.putString("cpName",cpUserName);
                            viewBundle.putString("pUserName",patientUserNamesList.get(pos).getUserID());
                            viewIntent.putExtras(viewBundle);
                            startActivity(viewIntent);
                        } else if (which == 1){
                            Toast.makeText(CareProviderListPatientsActivity.this,"Delete",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CareProviderListPatientsActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                modifyAlert.show();
                return false;
            }
        });
    }
}
