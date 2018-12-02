package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class AddPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        FloatingActionButton savePatientFAB = findViewById(R.id.savePatientFAB);
        savePatientFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText patientUserName = (EditText) findViewById(R.id.patientUserName);
                String patientUName = patientUserName.getText().toString();

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

                if (patientUName.equals("")){
                    Toast.makeText(AddPatientActivity.this,"Nothing entered. Please enter a valid username.",Toast.LENGTH_SHORT).show();
                } else if (!val){
                    Toast.makeText(AddPatientActivity.this,"Username does not exist. Please enter valid username.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddPatientActivity.this,"Saving "+patientUName, Toast.LENGTH_SHORT).show();

                    Intent savePatient = new Intent(AddPatientActivity.this,CareProviderListPatientsActivity.class);
                    savePatient.putExtra("UNAME",patientUName);
                    startActivity(savePatient);
                }


            }
        });
    }
}
