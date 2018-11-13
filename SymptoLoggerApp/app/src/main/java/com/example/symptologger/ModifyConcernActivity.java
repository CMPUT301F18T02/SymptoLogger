package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ModifyConcernActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_concern);

        Intent intent = getIntent();
        String concernToModify = intent.getStringExtra(ListConcernActivity.EXTRA_MESSAGE);
    }
}
