package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

public class ViewConcernActivity extends AppCompatActivity {

    Collection<Concern> concerns;
    ArrayList<Concern> concernList;
    int pos;

    ListView recordListView;
    ArrayList<Record> recordList;
    ArrayAdapter<Record> recordListAdapter;
    Collection<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_concern);

        Intent intent = getIntent();

        /*
        * passing integer from one activity to another:
        * https://stackoverflow.com/questions/7074097/how-to-pass-integer-from-one-activity-to-another/7074143#7074143
        * User: https://stackoverflow.com/users/379693/paresh-mayani, and
        * https://stackoverflow.com/users/63550/peter-mortensen
        * Date: 2018-11-13
        */

        pos = intent.getIntExtra("pos",0);


        concerns = ConcernListController.getConcernList().getConcerns();
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
        records = RecordListController.getRecordList().getRecords();
        recordList = new ArrayList<Record>(records);
        recordListAdapter = new ArrayAdapter<Record>(this,android.R.layout.simple_list_item_1,recordList);
        recordListView.setAdapter(recordListAdapter);
    }

    public void back() {
        Toast.makeText(this, "Back ...", Toast.LENGTH_SHORT).show();
        Intent backIntent = new Intent(ViewConcernActivity.this, ListConcernActivity.class);
        startActivity(backIntent);
    }

    public void modify(){
        Toast.makeText(this,"Modify ...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewConcernActivity.this, ModifyConcernActivity.class);
        intent.putExtra("pos",pos);
        startActivity(intent);
    }
}
