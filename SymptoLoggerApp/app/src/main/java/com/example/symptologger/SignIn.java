package com.example.symptologger;

import android.content.Intent;
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

        ElasticSearchClient.SearchRecord searchRecord = new ElasticSearchClient.SearchRecord();
        searchRecord.execute(userName2);

        userNameTest = "no";

        try {
            userNameTest = searchRecord.get();
            Toast.makeText(this, userNameTest,Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (userNameTest.equals(userName2)){
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void LogIn(View v) {
        if (verifyLogIn()==Boolean.TRUE){
            Intent intent = new Intent(SignIn.this, ListConcernActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Sorry, username "+userName2+" was not found. Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}


