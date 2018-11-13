package com.example.symptologger;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ListConcernActivity extends AppCompatActivity {

    //https://developer.android.com/training/basics/firstapp/starting-activity#java
    //2018-11-12
//    public static final String EXTRA_MESSAGE = "com.example.symptologger.MESSAGE";

    ListView concernListView;
    ArrayList<Concern> concernList;
    ArrayAdapter<Concern> concernListAdapter;
    Collection<Concern> concerns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_concern);
    }

    @Override
    protected void onStart(){
        super.onStart();

        concernListView = (ListView) findViewById(R.id.listConcernsView);
        concerns = ConcernListController.getConcernList().getConcerns();
        concernList = new ArrayList<Concern>(concerns);
        concernListAdapter = new ArrayAdapter<Concern>(this, android.R.layout.simple_list_item_1, concernList);
        concernListView.setAdapter(concernListAdapter);

        ConcernListController.getConcernList().addListener(new ConcernListener() {
            @Override
            public void updateListener() {
                concernList.clear();
                Collection<Concern> c = ConcernListController.getConcernList().getConcerns();
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
                            viewIntent.putExtra("pos",pos);
                            startActivity(viewIntent);
                        } else if (which == 1){
                            Toast.makeText(ListConcernActivity.this,"Delete",Toast.LENGTH_SHORT).show();
                            ConcernListController.getConcernList().deleteConcern(concernList.get(pos));
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

        concernListAdapter.notifyDataSetChanged();
    }


    public void addConcern(View view){
        Toast.makeText(this,"Add New Concern", Toast.LENGTH_SHORT).show();
        Intent newConcernIntent = new Intent(ListConcernActivity.this,NewConcernActivity.class);
        startActivity(newConcernIntent);
    }

    public void logOut(View view){
        Toast.makeText(this,"Good Bye! ...", Toast.LENGTH_SHORT).show();
    }
}
