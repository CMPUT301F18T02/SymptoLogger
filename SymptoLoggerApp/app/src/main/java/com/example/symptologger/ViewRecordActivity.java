package com.example.symptologger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
 * <p>
 *     Patient view of a record in two tabs.
 *     Details tab @see RecordDetailsFragment
 *     Comments tab @see RecordCommentFragment
 * </p>
 */

public class ViewRecordActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    Collection<Concern> concerns;
    ArrayList<Concern> concernList;
    ArrayList<Record> recordList;

    int CONCERN_POS;
    int RECORD_POS;

    Record recordToView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        addTabs(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        CONCERN_POS = extras.getInt("CONCERN");
        RECORD_POS = extras.getInt("RECORD");

        concerns = ConcernListController.getConcernList("").getConcernsList();
        concernList = new ArrayList<Concern>(concerns);

        Concern concernToView = concernList.get(CONCERN_POS);
        recordList = new ArrayList<Record>(concernToView.getRecords());

        recordToView = recordList.get(RECORD_POS);
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
