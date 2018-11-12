package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListConcernActivity extends AppCompatActivity {

    ListView concernListView;
    ArrayList<Concern> concernList;
    ArrayAdapter<Concern> concernListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_concern);
    }

    @Override
    protected void onStart(){
        super.onStart();

        concernListView = (ListView) findViewById(R.id.listConcernsView);
        concernListAdapter = new ArrayAdapter<Concern>(this, android.R.layout.simple_list_item_1, concernList);
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
