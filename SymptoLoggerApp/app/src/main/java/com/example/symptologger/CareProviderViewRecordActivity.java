package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;

public class CareProviderViewRecordActivity extends AppCompatActivity {

    private TabLayout cpTabLayout;
    private ViewPager cpViewPager;
    private Toolbar toolbar;

    Collection<Concern> patientConcerns;
    ArrayList<Concern> patientConcernList;
    ArrayList<Record> cpRecordList;

    int CP_CONCERN_POS;
    int CP_RECORD_POS;

    private String pUserName;

    Record cpRecordToView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_view_record);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cpViewPager = findViewById(R.id.cpViewpager);
        addTabs(cpViewPager);

        cpTabLayout = findViewById(R.id.cpTabs);
        cpTabLayout.setupWithViewPager(cpViewPager);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        CP_CONCERN_POS = extras.getInt("CP_CONCERN");
        CP_RECORD_POS = extras.getInt("CP_RECORD");
        pUserName = extras.getString("pUserName");

        patientConcerns = ConcernListController.getConcernList(pUserName).getConcernsList();
        patientConcernList = new ArrayList<Concern>(patientConcerns);

        Concern concernToView = patientConcernList.get(CP_CONCERN_POS);
        cpRecordList = new ArrayList<Record>(concernToView.getRecords());

        cpRecordToView = cpRecordList.get(CP_RECORD_POS);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RecordDetailsFragment(), "DETAILS");
        adapter.addFrag(new RecordCommentFragment(), "COMMENTS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_record_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.editOption:
                // TODO: edit a record
            case R.id.deleteOption:
                // TODO: delete a record, pops up a dialog
        }

        return super.onOptionsItemSelected(item);
    }
}
