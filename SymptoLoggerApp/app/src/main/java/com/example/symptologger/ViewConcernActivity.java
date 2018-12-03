package com.example.symptologger;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 * The activity that handles viewing the concern.
 */

public class ViewConcernActivity extends AppCompatActivity {

    Collection<Concern> concerns;
    ArrayList<Concern> concernList;
    int pos;

    ListView recordListView;
    ArrayList<Record> recordList;
    ArrayAdapter<Record> recordListAdapter;
    Collection<Record> records;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_concern);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pos = extras.getInt("pos");
        userName = extras.getString("userName");

        //Intent intent = getIntent();

        /*
        * passing integer from one activity to another:
        * https://stackoverflow.com/questions/7074097/how-to-pass-integer-from-one-activity-to-another/7074143#7074143
        * User: https://stackoverflow.com/users/379693/paresh-mayani, and
        * https://stackoverflow.com/users/63550/peter-mortensen
        * Date: 2018-11-13
        */

        //pos = intent.getIntExtra("pos",0);


        concerns = ConcernListController.getConcernList(userName).getConcernsList();
        concernList = new ArrayList<Concern>(concerns);

        Toast.makeText(this,"View "+concernList.get(pos).getTitle(),Toast.LENGTH_SHORT).show();

        FloatingActionButton modifyActivityFAB = findViewById(R.id.modifyActivityFAB);
        modifyActivityFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modify();
            }
        });

        FloatingActionButton backFAB = findViewById(R.id.backFAB);
        backFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView concernTitleView = (TextView) findViewById(R.id.concernTitleView);
        concernTitleView.setText(concernList.get(pos).getTitle());

        TextView concernDateView = (TextView) findViewById(R.id.concernDateView);
        concernDateView.setText(concernList.get(pos).getDate().toString());

        TextView concernDescriptionView = (TextView) findViewById(R.id.concernDescriptionView);
        concernDescriptionView.setText(concernList.get(pos).getDescription());

        recordListView = findViewById(R.id.recordListView);
        //records = RecordListController.getRecordList().getRecords();
        recordList = new ArrayList<Record>(concernList.get(pos).getRecords());

        Log.d("record list", String.valueOf(recordList));

        recordListAdapter = new ArrayAdapter<Record>(this,android.R.layout.simple_list_item_1,recordList);
        recordListView.setAdapter(recordListAdapter);

        recordListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
                final int recPos = position;

                AlertDialog.Builder recordAlert = new AlertDialog.Builder(ViewConcernActivity.this);

                recordAlert.setTitle(recordList.get(recPos).getTitle());
                recordAlert.setCancelable(true);

                CharSequence[] options = {"View",
                        "Delete",
                        "Cancel"};

                recordAlert.setItems(options, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        if (which == 0){
                            Toast.makeText(ViewConcernActivity.this,recordList.get(recPos).getTitle(),Toast.LENGTH_SHORT).show();
                            Intent viewIntent = new Intent(ViewConcernActivity.this, ViewRecordActivity.class);
                            Bundle extras = new Bundle();
                            extras.putInt("CONCERN",pos);
                            extras.putInt("RECORD",recPos);
                            extras.putString("USERNAME", userName);
                            viewIntent.putExtras(extras);
                            startActivity(viewIntent);
                        } else if (which == 1){
                            Toast.makeText(ViewConcernActivity.this,"Delete",Toast.LENGTH_SHORT).show();
                            concernList.get(pos).removeRecord(recordList.get(recPos));
                            recordList = new ArrayList<Record>(concernList.get(pos).getRecords());
                            recordListAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ViewConcernActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                recordAlert.show();
                return false;
            }
        });
    }

    public void back() {
        Toast.makeText(this, "Back ...", Toast.LENGTH_SHORT).show();
        Intent backIntent = new Intent(ViewConcernActivity.this, ListConcernActivity.class);
        backIntent.putExtra("userName",userName);
        startActivity(backIntent);
    }

    public void modify(){
        Toast.makeText(this,"Modify ...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewConcernActivity.this, ModifyConcernActivity.class);
        Bundle modifyExtras = new Bundle();
        modifyExtras.putInt("pos",pos);
        modifyExtras.putString("userName",userName);
        intent.putExtras(modifyExtras);
        startActivity(intent);
    }
}
