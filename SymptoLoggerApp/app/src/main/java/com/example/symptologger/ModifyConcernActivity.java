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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int posToModify;
    Collection<Concern> concerns;
    ArrayList<Concern> concernList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_concern);

        Intent intent = getIntent();
        posToModify = Integer.parseInt(intent.getStringExtra(ListConcernActivity.EXTRA_MESSAGE)); //https://www.mkyong.com/java/java-convert-string-to-int/, 2018-11-12
    }

    public void modifyConcern(View view) throws ParseException {

        concerns = ConcernListController.getConcernList().getConcerns();
        concernList = new ArrayList<Concern>(concerns);
        
        Concern modConcern = concernList.get(posToModify);

        Toast.makeText(this,"Modifying ...", Toast.LENGTH_SHORT).show();

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

        if (!concernTitle.equals("")){
            modConcern.setTitle(concernTitle);
        }

        if (!concernDate.equals("")){
            modConcern.setDate(sdf.parse(concernDate));
        }

        if (!concernDescription.equals("")) {
            modConcern.setDescription(concernDescription);
        }

        Intent doneIntent = new Intent(ModifyConcernActivity.this, ListConcernActivity.class);
        startActivity(doneIntent);
    }

    public void cancel(View view){
        Toast.makeText(this,"Cancel ...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifyConcernActivity.this, ListConcernActivity.class);
    }
}
