package com.example.symptologger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
 * The main activity that starts when the user opens the app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreference sp = new SharedPreference();
        Integer status = sp.getLogInStatus(getApplicationContext());
        String userName = sp.loadUserName(getApplicationContext());

        if (!CheckServerAvailability.getTimerStart()) {
            CheckServerAvailability.startIsAvailableTimer();
        }

        if (status == 1) {
            Intent intent = new Intent(MainActivity.this, ListConcernActivity.class);
            intent.putExtra("USERNAME",userName);
            startActivity(intent);
        } else if ((status == 2)){
            Intent intent = new Intent(MainActivity.this, CareProviderListPatientsActivity.class);
            intent.putExtra("USERNAME",userName);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
            //new ElasticSearchClient.AddShareCodeTable().execute();
            Button button_sign_up = (Button) findViewById(R.id.button_sign_up);
            //Button button_add_geo_location = (Button) findViewById(R.id.button_geo_location);

            button_sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signUpIntent = new Intent(MainActivity.this, CreateProfileActivity.class);
                    startActivity(signUpIntent);
                }
            });
        }
    }

    public void SignIn(View v) {
        Intent SignInIntent = new Intent(this, SignInActivity.class);
        startActivity(SignInIntent);

    }
}

