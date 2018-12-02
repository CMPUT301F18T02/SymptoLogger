package com.example.symptologger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

/**
 * The activity that allows the user to create a new profile. It will get the profile details and
 * then send them off to the ElasticSearchClient object for interacting with the server.
 */

public class CreateProfileActivity extends AppCompatActivity{

    View mView;
    String user_id;
    //String first_name;
    //String last_name;
    String phone;
    String email;
    String user_type;

    Patient patient;
    CareProvider care_provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_create_profile);
        mView = findViewById(R.id.view_create_profile);


        //generateUserId();
        addListenerOnRadioGroup();
        addButtonSignUpListener();

    }


    public void addButtonSignUpListener() {
        Button button_sign_up = (Button) mView.findViewById(R.id.button_sign_up);
        final EditText editText_user_id = (EditText) mView.findViewById(R.id.editTextUserId);
        //final EditText editText_first_name = (EditText) mView.findViewById(R.id.editText_first_name);
        //final EditText editText_last_name = (EditText) mView.findViewById(R.id.editText_last_name);
        final EditText editText_phone= (EditText) mView.findViewById(R.id.editText_phone_number);
        final EditText editText_email= (EditText) mView.findViewById(R.id.editText_email);

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_type == null) {
                    Toast.makeText(CreateProfileActivity.this,
                            "Please select user type", Toast.LENGTH_SHORT).show();
                } else {
                    user_id = String.valueOf(editText_user_id.getText());
                    //first_name = String.valueOf(editText_first_name.getText());
                    //last_name = String.valueOf(editText_last_name.getText());
                    phone = String.valueOf(editText_phone.getText());
                    email = String.valueOf(editText_email.getText());
                    if ((user_id.length()<8)||(user_id.equals(""))){
                        Toast.makeText(CreateProfileActivity.this,"Please enter a user id that is at least 8 characters long.", Toast.LENGTH_SHORT).show();
                    } else if (phone.equals("")) {
                        Toast.makeText(CreateProfileActivity.this,"Please enter a phone number.",Toast.LENGTH_SHORT).show();
                    } else if (email.equals("")){
                        Toast.makeText(CreateProfileActivity.this,"Please enter an email address.",Toast.LENGTH_SHORT).show();
                    } else {
                        createUser();
                    }
                }
            }
        });
    }


    public void addListenerOnRadioGroup() {
        RadioGroup radioGroup_user = (RadioGroup) mView.findViewById(R.id.radioGroup_user_type);
        radioGroup_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton_user = (RadioButton) findViewById(checkedId);
                user_type = (String) radioButton_user.getText();
            }
        });
    }


    public void createUser() {
        // TODO: load database
        // Just add user to UserList for now, ignore PatientList & CareProviderList
        UserList userList = new UserList();

        if (user_type.equals("Patient")) {
            //patient = new Patient(user_id, first_name, last_name, email, phone, user_type);
            patient = new Patient(user_id, email, phone, user_type);
            userList.addUser(patient);
        } else if (user_type.equals("Care Provider")){
            //care_provider = new CareProvider(user_id, first_name, last_name, email, phone, user_type);
            care_provider = new CareProvider(user_id, email, phone, user_type);
            userList.addUser(care_provider);
        }


        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("MST"));
        Date date = new Date();


        Boolean isInUse = Boolean.FALSE;
        ElasticSearchClient.SearchUser searchUser = new ElasticSearchClient.SearchUser();
        searchUser.execute(user_id);
        try {
            isInUse = searchUser.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!isInUse) {

            Boolean val = Boolean.FALSE;
            ElasticSearchClient.AddUser addUser = new ElasticSearchClient.AddUser();
            addUser.execute(user_id, formatter.format(date), user_type, email, phone);

            try {
                val = addUser.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!val) {
                Toast.makeText(CreateProfileActivity.this, "Failed to add new user " + user_id + ". Please try again.", Toast.LENGTH_SHORT).show();
            } else {
                if(user_type.equals("Patient")){
                    ElasticSearchClient.AddPatient addPatient = new ElasticSearchClient.AddPatient();
                    addPatient.execute(user_id,email,phone,"",new Date().toString());
                } else {
                    //TODO Add care provider ES
                }
                Toast.makeText(CreateProfileActivity.this, "Successfully added " + user_id + " to the user list.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(CreateProfileActivity.this,"User id already in use. Please enter a unique user id.",Toast.LENGTH_SHORT).show();
        }
    }

    /* Generate user id from 1000 ~ 9999 */
//    public void generateUserId() {
//        Random rand = new Random();
//        int random_id = rand.nextInt((9999) + 1000);
//        user_id = String.valueOf(random_id);
//        TextView textView_user_id = mView.findViewById(R.id.textView_user_id);
//        textView_user_id.setText(user_id);
//    }

}
