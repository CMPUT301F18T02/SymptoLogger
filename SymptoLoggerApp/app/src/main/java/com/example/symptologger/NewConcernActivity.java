package com.example.symptologger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class NewConcernActivity extends AppCompatActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Concern newConcern; //New concern variable declared ...
    Date blankDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_concern);

        FloatingActionButton saveFAB = findViewById(R.id.saveNewFAB);
        saveFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    saveConcern();
                } catch (ParseException e){
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton cancelNewFAB = findViewById(R.id.cancelNewFAB);
        cancelNewFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewConcernActivity.this,"Cancel ...", Toast.LENGTH_SHORT).show();
                Intent cancelIntent = new Intent(NewConcernActivity.this,ListConcernActivity.class);
                startActivity(cancelIntent);
            }
        });
    }

    public void saveConcern() throws ParseException {

        ConcernListController clc = new ConcernListController();



        Toast.makeText(this,"Saving ...", Toast.LENGTH_SHORT).show();

        EditText editTitle = (EditText) findViewById(R.id.concernTitle);
        EditText editDescription = (EditText) findViewById(R.id.concernDescription);
        EditText editDate = (EditText) findViewById(R.id.concernDate);

        String concernTitle = editTitle.getText().toString();
        String concernDescription = editDescription.getText().toString();
        String concernDate = editDate.getText().toString();

        /*
        That EditText.getText() does not return null, but empty string ...
        https://stackoverflow.com/questions/19590472/does-edittext-gettext-ever-returns-null
        User: https://stackoverflow.com/users/603742/stinepike
        Date: 2018-11-11
        */

        if (concernDate.equals("")){
            blankDate = new Date();
        } else {
            blankDate = sdf.parse(concernDate);
        }

        try {
            newConcern = new Concern(concernTitle,blankDate,concernDescription);
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException f){
            f.printStackTrace();
        }

        clc.addConcern(newConcern);

        Intent doneIntent = new Intent(NewConcernActivity.this, ListConcernActivity.class);
        startActivity(doneIntent);
    }
}
