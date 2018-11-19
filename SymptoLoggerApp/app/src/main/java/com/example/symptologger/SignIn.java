package com.example.symptologger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SignIn extends AppCompatActivity {

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

    public void LogIn(View v) {
        if (verifyLogIn()){
            Intent intent = new Intent(SignIn.this, ListConcernActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Sorry, username "+userName2+" was not found. Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}


