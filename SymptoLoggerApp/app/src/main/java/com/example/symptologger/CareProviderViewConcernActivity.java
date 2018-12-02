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
 * CareProviderViewconcernActivity is the feature that allows care providers to view a particular
 * concern of their patient.
 *
 * @author Patrick Tamm
 */

public class CareProviderViewConcernActivity extends AppCompatActivity {

    private String pUserName;
    private int pos;

    Collection<Concern> patientConcerns;
    ArrayList<Concern> patientConcernList;

    ListView cpRecordListView;
    ArrayList<Record> cpRecordList;
    ArrayAdapter<Record> cpRecordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_view_concern);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pUserName = extras.getString("pUserName");
        pos = extras.getInt("pos");

        patientConcerns = ConcernListController.getConcernList(pUserName).getConcernsList();
        patientConcernList = new ArrayList<Concern>(patientConcerns);

        FloatingActionButton backFAB = findViewById(R.id.cpBackFAB);
        backFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CareProviderViewConcernActivity.this, "Back ...", Toast.LENGTH_SHORT).show();
                Intent backIntent = new Intent(CareProviderViewConcernActivity.this, CareProviderViewPatientConcernsActivity.class);
                backIntent.putExtra("pUserName",pUserName);
                startActivity(backIntent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView concernTitleView = (TextView) findViewById(R.id.cpConcernTitleView);
        concernTitleView.setText(patientConcernList.get(pos).getTitle());

        TextView concernDateView = (TextView) findViewById(R.id.cpConcernDateView);
        concernDateView.setText(patientConcernList.get(pos).getDate().toString());

        TextView concernDescriptionView = (TextView) findViewById(R.id.cpConcernDescriptionView);
        concernDescriptionView.setText(patientConcernList.get(pos).getDescription());

        cpRecordListView = findViewById(R.id.cpRecordListView);
        //records = RecordListController.getRecordList().getRecords();
        cpRecordList = new ArrayList<Record>(patientConcernList.get(pos).getRecords());
        cpRecordListAdapter = new ArrayAdapter<Record>(this,android.R.layout.simple_list_item_1,cpRecordList);
        cpRecordListView.setAdapter(cpRecordListAdapter);

        cpRecordListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
                final int recPos = position;

                AlertDialog.Builder recordAlert = new AlertDialog.Builder(CareProviderViewConcernActivity.this);

                recordAlert.setTitle(cpRecordList.get(recPos).getTitle());
                recordAlert.setCancelable(true);

                CharSequence[] options = {"View",
                        "Cancel"};

                recordAlert.setItems(options, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        if (which == 0){
                            Toast.makeText(CareProviderViewConcernActivity.this,cpRecordList.get(recPos).getTitle(),Toast.LENGTH_SHORT).show();
                            Intent viewIntent = new Intent(CareProviderViewConcernActivity.this, CareProviderViewRecordActivity.class);
                            Bundle extras = new Bundle();
                            extras.putInt("CP_CONCERN",pos);
                            extras.putInt("CP_RECORD",recPos);
                            extras.putString("pUserName",pUserName);
                            viewIntent.putExtras(extras);
                            startActivity(viewIntent);
                        } else {
                            Toast.makeText(CareProviderViewConcernActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                recordAlert.show();
                return false;
            }
        });
    }
}
