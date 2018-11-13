package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ModifyConcernActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_concern);

        Intent intent = getIntent();
        String concernToModify = intent.getStringExtra(ListConcernActivity.EXTRA_MESSAGE);
    }

    public void modifyConcern(View view){

    }

    public void cancel(View view){
        Toast.makeText(this,"Cancel ...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifyConcernActivity.this, ListConcernActivity.class);
    }
}
