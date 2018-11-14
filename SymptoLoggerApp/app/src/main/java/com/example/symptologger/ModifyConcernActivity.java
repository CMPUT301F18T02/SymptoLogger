package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ModifyConcernActivity extends AppCompatActivity {

    private int pos;
    Collection<Concern> concerns;
    ArrayList<Concern> concernList;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_concern);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);

        concerns = ConcernListController.getConcernList().getConcerns();
        concernList = new ArrayList<Concern>(concerns);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void modifyConcern(View view) throws ParseException {
        Toast.makeText(this,"Modifying ...",Toast.LENGTH_SHORT).show();

        EditText newTitle = (EditText) findViewById(R.id.modifyConcernTitle);
        EditText newDescription = (EditText) findViewById(R.id.modifyConcernDesc);
        EditText newDate = (EditText) findViewById(R.id.modifyConcernDate);

        String title = newTitle.getText().toString();
        String description = newDescription.getText().toString();
        String date = newDate.getText().toString();

        if (!title.equals("")){
            try {
                concernList.get(pos).setTitle(title);
            } catch (TitleTooLongException e) {
                e.printStackTrace();
                Toast.makeText(this,"Title too long: 30 characters maximum",Toast.LENGTH_LONG).show();
            }
        }

        if (!description.equals("")){
            concernList.get(pos).setDescription(description);
        }

        if (!date.equals("")){
            Date blankDate = sdf.parse(date);
            concernList.get(pos).setDate(blankDate);
        }

        Intent intent = new Intent(ModifyConcernActivity.this,ViewConcernActivity.class);
        intent.putExtra("pos",pos);
        startActivity(intent);
    }

    public void addRecord(View view){
        Toast.makeText(this,"Add record ...", Toast.LENGTH_SHORT).show();
    }

    public void cancel(View view){
        Toast.makeText(this,"Cancel ...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifyConcernActivity.this, ViewConcernActivity.class);
        startActivity(intent);
    }
}
