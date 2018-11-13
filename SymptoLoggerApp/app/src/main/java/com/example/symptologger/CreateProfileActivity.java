package com.example.symptologger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateProfileActivity extends AppCompatActivity{

    View mView;
    String user_id;
    String first_name;
    String last_name;
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

        final EditText editText_user_id = (EditText) mView.findViewById(R.id.editText_user_id);
        final EditText editText_first_name = (EditText) mView.findViewById(R.id.editText_user_id);
        final EditText editText_last_name = (EditText) mView.findViewById(R.id.editText_user_id);
        final EditText editText_phone= (EditText) mView.findViewById(R.id.editText_user_id);
        final EditText editText_email= (EditText) mView.findViewById(R.id.editText_user_id);
        Button button_sign_up = (Button) mView.findViewById(R.id.button_sign_up);

        addListenerOnRadioGroup();

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_type == null) {
                    Toast.makeText(CreateProfileActivity.this,
                            "Please select user type", Toast.LENGTH_SHORT).show();
                } else {
                    user_id = String.valueOf(editText_user_id.getText());
                    first_name = String.valueOf(editText_first_name.getText());
                    last_name = String.valueOf(editText_last_name.getText());
                    phone = String.valueOf(editText_phone.getText());
                    email = String.valueOf(editText_email.getText());
                    createUser();
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
            patient = new Patient(user_id, first_name, last_name, email, phone, user_type);
            userList.addUser(patient);
        } else if (user_type.equals("Care Provider")){
            care_provider = new CareProvider(user_id, first_name, last_name, email, phone, user_type);
            userList.addUser(care_provider);
        }

//        Log.d("user", String.valueOf(userList.getUserList().get(0)));

        // TODO: save to database
    }

}
