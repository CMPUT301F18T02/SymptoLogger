package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new ElasticSearchClient.AddTable().execute();
        //new ElasticSearchClient.AddRecord().execute();
        //new ElasticSearchClient.SearchRecord().execute("no");
    }

    public void SignIn(View v) {
        Intent SignInIntent = new Intent(this, SignIn.class);
        startActivity(SignInIntent);
    }
}
