package com.example.symptologger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                Intent passedIntent = new Intent(PatientEntersShareCodeActivity.this,ListConcernActivity.class);
                passedIntent.putExtra("userName",userName);
                startActivity(passedIntent);
            } else {
                Toast.makeText(this,"Share code does not match. Regenerate code on first device.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
