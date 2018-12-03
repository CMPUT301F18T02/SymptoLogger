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
 * The feature that allows care providers to view a record in a concern. They will have the option
 * of commenting on the record, but not modifying it. NOTE: The code is copied from the ViewRecordActivity
 * class, with some slight modifications.
 *
 * @author Noni Hua, Patrick Tamm
 */
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

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        CP_CONCERN_POS = extras.getInt("CP_CONCERN");
//        CP_RECORD_POS = extras.getInt("CP_RECORD");
//        pUserName = extras.getString("pUserName");
//
//        patientConcerns = ConcernListController.getConcernList(pUserName).getConcernsList();
//        patientConcernList = new ArrayList<Concern>(patientConcerns);
//
//        Concern concernToView = patientConcernList.get(CP_CONCERN_POS);
//        cpRecordList = new ArrayList<Record>(concernToView.getRecords());
//
//        cpRecordToView = cpRecordList.get(CP_RECORD_POS);

//        toolbar = findViewById(R.id.cpToolbar);
//        setSupportActionBar(toolbar);

        cpViewPager = findViewById(R.id.cpViewpager);
        addTabs(cpViewPager);

        cpTabLayout = findViewById(R.id.cpTabs);
        cpTabLayout.setupWithViewPager(cpViewPager);


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
