package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;
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

public class GenShareCodeActivity extends AppCompatActivity {

    private String userName;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_share_code);

        TextView codeView = findViewById(R.id.codeTextView);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        code = genRandNumString();
        codeView.setText(code);

        ElasticSearchClient.AddShareCode addShareCode = new ElasticSearchClient.AddShareCode();
        addShareCode.execute(userName,code,new Date().toString());

        Boolean isAdded = Boolean.FALSE;
        try {
            isAdded = addShareCode.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (!isAdded){
            Toast.makeText(this, "Error in adding share code to ElasticSearch server",Toast.LENGTH_SHORT).show();
        }
    }

    //Kaiwen Zhang's code was  copied for below.
    //

    public String genRandNumString(){
        Random rand = new Random();
        int random_code = rand.nextInt(10000);

        //We want 4 digits, so here's a hack way of doing it ...
        if (random_code<1000){
            random_code = random_code+1000;
        }

        String shareCode = String.valueOf(random_code);
        return shareCode;
    }

    public void doneWithCode(View view){

        ElasticSearchClient.DeleteShareCode deleteShareCode = new ElasticSearchClient.DeleteShareCode();
        deleteShareCode.execute(userName,code);

        Intent intent = new Intent(GenShareCodeActivity.this, ListConcernActivity.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
    }
}
