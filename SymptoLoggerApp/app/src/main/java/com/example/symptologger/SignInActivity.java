package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

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
 * The activity that handles the sign in procedure.
 */

public class SignInActivity extends AppCompatActivity {

    private String userName2;
    private EditText typedUserName;
    private String userNameTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        typedUserName = findViewById(R.id.typedUserName);
    }

    private Boolean verifyLogIn(){
        userName2 = typedUserName.getText().toString();
        Boolean val = Boolean.FALSE;

        ElasticSearchClient.SearchUser searchUser = new ElasticSearchClient.SearchUser();
        searchUser.execute(userName2);

        try {
            val = searchUser.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return val;
    }

    private String determineRole(){
        ElasticSearchClient.GetUserRole getUserRole = new ElasticSearchClient.GetUserRole();
        getUserRole.execute(userName2);

        String role = "";

        try {
            role = getUserRole.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return role;
    }

    public void LogIn(View v) {
        if (verifyLogIn()){
            if (determineRole().equals("Patient")){
                Intent intent = new Intent(SignInActivity.this, ListConcernActivity.class);
                startActivity(intent);
            } else if (determineRole().equals("Care Provider")){
                Intent intent = new Intent(SignInActivity.this, CareProviderListPatientsActivity.class);
                startActivity(intent);
            } else {
                Log.e("Error","No role found for user from elasticsearch.");
                Toast.makeText(this,"No role found for user",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Sorry, username "+userName2+" was not found. Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}


