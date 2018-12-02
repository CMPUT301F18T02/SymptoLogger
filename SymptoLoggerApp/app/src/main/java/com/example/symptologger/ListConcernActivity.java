package com.example.symptologger;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
 * The activity that allows the user to view the list of concerns.
 */

public class ListConcernActivity extends AppCompatActivity {

    //https://developer.android.com/training/basics/firstapp/starting-activity#java
    //2018-11-12
//    public static final String EXTRA_MESSAGE = "com.example.symptologger.MESSAGE";

    ListView concernListView;
    ArrayList<Concern> concernList;
    ArrayAdapter<Concern> concernListAdapter;
    Collection<Concern> concerns;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_concern);

        Intent fromSignIn = getIntent();
        userName = fromSignIn.getStringExtra("userName");

        TextView userTextView = findViewById(R.id.userNameTextView);
        userTextView.setText(userName);

        /*https://developer.android.com/guide/topics/ui/floating-action-button#java
        * Date: 2018-11-17*/
        FloatingActionButton addNewFAB = findViewById(R.id.addConcernFAB);
        addNewFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListConcernActivity.this,"Add New Concern", Toast.LENGTH_SHORT).show();
                Intent newConcernIntent = new Intent(ListConcernActivity.this,NewConcernActivity.class);
                newConcernIntent.putExtra("userName",userName);
                startActivity(newConcernIntent);
            }
        });

        FloatingActionButton logOutFAB = findViewById(R.id.logOutFAB);
        logOutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListConcernActivity.this,"Good Bye", Toast.LENGTH_SHORT).show();
                Intent logOut = new Intent(ListConcernActivity.this,MainActivity.class);
                startActivity(logOut);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        concernListView = (ListView) findViewById(R.id.listConcernsView);
        concerns = ConcernListController.getConcernList(userName).getConcernsList();
        concernList = new ArrayList<Concern>(concerns);
        concernListAdapter = new ArrayAdapter<Concern>(this, android.R.layout.simple_list_item_1, concernList);
        concernListView.setAdapter(concernListAdapter);

        ConcernListController.getConcernList(userName).addListener(new ConcernListener() {
            @Override
            public void updateListener() {
                concernList.clear();
                Collection<Concern> c = ConcernListController.getConcernList(userName).getConcernsList();
                concernList.addAll(c);
                concernListAdapter.notifyDataSetChanged();
            }
        });

        concernListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
                final int pos = position;

                AlertDialog.Builder modifyAlert = new AlertDialog.Builder(ListConcernActivity.this);

                modifyAlert.setTitle(concernList.get(pos).getTitle());
                modifyAlert.setCancelable(true);

                CharSequence[] options = {"View",
                                          "Delete",
                                          "Cancel"};

                modifyAlert.setItems(options, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        if (which == 0){
                            Intent viewIntent = new Intent(ListConcernActivity.this, ViewConcernActivity.class);
                            Bundle viewBundle = new Bundle();
                            viewBundle.putInt("pos",pos);
                            viewBundle.putString("userName",userName);
                            viewIntent.putExtras(viewBundle);
                            startActivity(viewIntent);
                        } else if (which == 1){
                            Toast.makeText(ListConcernActivity.this,"Delete",Toast.LENGTH_SHORT).show();
                            ConcernListController.getConcernList(userName).deleteConcern(concernList.get(pos));
                            Intent restart = new Intent(ListConcernActivity.this,ListConcernActivity.class);
                            restart.putExtra("userName",userName);
                            startActivity(restart);
//                            concerns = ConcernListController.getConcernList(userName).getConcernsList();
//                            concernList = new ArrayList<Concern>(concerns);
//                            concernListAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ListConcernActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                modifyAlert.show();

                return false;
            }
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        concerns = ConcernListController.getConcernList(userName).getConcernsList();
        concernList = new ArrayList<Concern>(concerns);
        concernListAdapter.notifyDataSetChanged();
    }

    public void shareProfile(View view){
        Toast.makeText(this,"Generating share code ...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ListConcernActivity.this,GenShareCodeActivity.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
