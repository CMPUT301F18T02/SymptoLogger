package com.example.symptologger;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
 * <p>
 *     Care Provider view of a record in two tabs.
 *     Details tab @see RecordDetailsFragment
 *     Comments tab @see RecordCommentFragment
 * </p>
 */

public class CareProviderViewRecordActivity extends AppCompatActivity {

    private TabLayout cpTabLayout;
    private ViewPager cpViewPager;
    private Toolbar cpToolBar;
    private TextView recordTitle;

    int CONCERN_POS;
    int RECORD_POS;
    String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider_view_record);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        try {
            CONCERN_POS = extras.getInt("CONCERN");
            RECORD_POS = extras.getInt("RECORD");
            USERNAME = extras.getString("USERNAME");
        } catch (Exception e) {
            // TODO: this is for testing only
            CONCERN_POS = 0;
            RECORD_POS = 0;
            USERNAME = "12345678";
        }

        Collection<Concern> concerns = ConcernListController.getConcernList(USERNAME).getConcernsList();
        ArrayList<Concern> concernList = new ArrayList<Concern>(concerns);
        Concern concernToView = concernList.get(CONCERN_POS);
        ArrayList<Record> recordList = new ArrayList<Record>(concernToView.getRecords());

        Record record = recordList.get(RECORD_POS);

        cpToolBar = findViewById(R.id.cpToolbar);
        setSupportActionBar(cpToolBar);

        recordTitle = findViewById(R.id.viewRecordTitleText);
        recordTitle.setText(record.getTitle());

        cpViewPager = findViewById(R.id.cpViewpager);
        addTabs(cpViewPager);

        cpTabLayout = findViewById(R.id.cpTabs);
        cpTabLayout.setupWithViewPager(cpViewPager);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putInt("RECORD_POS", RECORD_POS);
        bundle.putInt("CONCERN_POS", CONCERN_POS);
        bundle.putString("USERNAME", USERNAME);

        RecordDetailsFragment detailsFragment = new RecordDetailsFragment();
        RecordCommentFragment commentFragment = new RecordCommentFragment();

        detailsFragment.setArguments(bundle);
        commentFragment.setArguments(bundle);

        adapter.addFrag(detailsFragment, "DETAILS");
        adapter.addFrag(commentFragment, "COMMENTS");
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
                Log.d("DEBUG", "edit option id is " + R.id.editOption);

//                // TODO: edit a record; should load the current record information
//                Intent editRecordIntent = new Intent(ViewRecordActivity.this, NewRecordActivity.class);
//
//                Bundle extras = new Bundle();
//                extras.putInt("RECORD_POS", RECORD_POS);
//                extras.putInt("CONCERN_POS", CONCERN_POS);
//                extras.putString("USERNAME", USERNAME);
//                editRecordIntent.putExtras(extras);
//
//                startActivity(editRecordIntent);
                break;
            case R.id.deleteOption:
                Log.d("DEBUG", "delete option id is " + R.id.deleteOption);

//                // TODO: delete a record, pops up a dialog
//                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(ViewRecordActivity.this);
//
//                deleteAlert.setTitle("Delete this record?");
//                deleteAlert.setCancelable(true);
//
//                CharSequence[] options = {"Yes", "Cancel"};
//
//                deleteAlert.setItems(options, new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which){
//                        if (which == 0){
//                            // TODO: update shared preference
//                            Toast.makeText(ViewRecordActivity.this, "Record deleted",Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(ViewRecordActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                deleteAlert.show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
