package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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


public class PatientEntersShareCodeActivity extends AppCompatActivity {

    private String userName;
    private String checkCode;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_enters_share_code);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userName = extras.getString("userName");
        checkCode = extras.getString("checkCode");
        Toast.makeText(this,"Check code: "+checkCode,Toast.LENGTH_SHORT).show();

    }

    private boolean verifyShareCode(){
        if (code.equals(checkCode)){
            return true;
        } else {
            return false;
        }
    }

    public void check(View view){
        EditText enteredCodeText = (EditText) findViewById(R.id.enterCodeText);
        code = enteredCodeText.getText().toString();

        if (code.equals("")){
            Toast.makeText(this, "Please enter share code. You won't be able to login otherwise.", Toast.LENGTH_SHORT).show();
        } else {
            if (verifyShareCode()){
                ElasticSearchClient.DeleteShareCode deleteShareCode = new ElasticSearchClient.DeleteShareCode();
                deleteShareCode.execute(userName,code); //delete the share code after successful login

                Intent passedIntent = new Intent(PatientEntersShareCodeActivity.this,ListConcernActivity.class);
                passedIntent.putExtra("userName",userName);
                startActivity(passedIntent);
            } else {
                Toast.makeText(this,"Share code does not match. Regenerate code on first device.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
