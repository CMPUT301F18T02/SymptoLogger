package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new ElasticSearchClient.AddTable().execute();
        //new ElasticSearchClient.AddRecord().execute();
        //new ElasticSearchClient.SearchRecord().execute("no");

        Button button_sign_up = (Button) findViewById(R.id.button_sign_up);
        //Button button_add_geo_location = (Button) findViewById(R.id.button_geo_location);

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateProfileActivity.class));
            }
        });

//        button_add_geo_location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MapsActivity.class));
//            }
//        });
    }

    public void SignIn(View v) {
        Intent SignInIntent = new Intent(this, SignInActivity.class);
        startActivity(SignInIntent);
//        Intent intent = new Intent(MainActivity.this, ListConcernActivity.class);
//        startActivity(intent);


    }
}

