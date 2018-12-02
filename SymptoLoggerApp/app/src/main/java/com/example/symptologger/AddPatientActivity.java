package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class AddPatientActivity extends AppCompatActivity {

    String patientUName;
    String cpUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        Intent intent = getIntent();
        cpUserName = intent.getStringExtra("cpUserName");

        FloatingActionButton savePatientFAB = findViewById(R.id.savePatientFAB);
        savePatientFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText patientUserName = (EditText) findViewById(R.id.patientUserName);
                patientUName = patientUserName.getText().toString();


                if (patientUName.equals("")) {
                    Toast.makeText(AddPatientActivity.this, "Nothing entered. Please enter a valid username.", Toast.LENGTH_SHORT).show();
                }

                EditText shareCodeText = (EditText) findViewById(R.id.enterShareCodeText);
                String shareCode = shareCodeText.getText().toString();

                Boolean val = Boolean.FALSE;

                ElasticSearchClient.SearchUser searchUser = new ElasticSearchClient.SearchUser();
                searchUser.execute(patientUName);

                try {
                    val = searchUser.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!val){
                    Toast.makeText(AddPatientActivity.this,"Username does not exist. Please enter valid username.",Toast.LENGTH_SHORT).show();
                } else {
                    if (shareCode.equals("")){
                        Toast.makeText(AddPatientActivity.this,"Please enter the share code.",Toast.LENGTH_SHORT).show();
                    } else {
                        if (getShareCode().equals("")){
                            Toast.makeText(AddPatientActivity.this,"The share code was not found. There may be an issue with the server.",Toast.LENGTH_SHORT).show();
                        } else {
                            ElasticSearchClient.GetSinglePatient getSinglePatient = new ElasticSearchClient.GetSinglePatient();
                            getSinglePatient.execute(patientUName);
                            Patient patient = null;
                            try {
                                patient = getSinglePatient.get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            String pEmail = patient.getEmail();
                            String pCell = patient.getCell();

                            ElasticSearchClient.DeletePatient deletePatient = new ElasticSearchClient.DeletePatient();
                            deletePatient.execute(patientUName);

                            ElasticSearchClient.AddPatient addPatient = new ElasticSearchClient.AddPatient();
                            addPatient.execute(patientUName,pEmail,pCell,cpUserName, new Date().toString());
                        }
                    }

                    Toast.makeText(AddPatientActivity.this,"Saving "+patientUName, Toast.LENGTH_SHORT).show();

                    Intent savePatient = new Intent(AddPatientActivity.this,CareProviderListPatientsActivity.class);
                    savePatient.putExtra("UNAME",patientUName);
                    startActivity(savePatient);
                }


            }
        });
    }

    public String getShareCode(){
        ElasticSearchClient.GetShareCode getShareCode = new ElasticSearchClient.GetShareCode();
        getShareCode.execute(patientUName);

        String code = "";
        try {
            code = getShareCode.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return code;
    }
}
