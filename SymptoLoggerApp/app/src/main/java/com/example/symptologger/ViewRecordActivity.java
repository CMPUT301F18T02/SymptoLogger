package com.example.symptologger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewRecordActivity extends AppCompatActivity {

    private CareProvider careProvider = new CareProvider("002", "First", "Last", "test@test.com", "123456790");

    private String careProviderName = "";
    private String description = "";
    private String datetime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        Record record = new Record();
        // TODO: choose logged in user
        Patient patient = new Patient("001", "First", "Last", "test@test.com", "123456790");

        careProviderName = careProvider.getFullName();
        datetime = record.getDate().toString();

        TextView careProviderView = (TextView) findViewById(R.id.careProviderContent);
        careProviderView.setText(careProviderName);

        TextView datetimeView = (TextView) findViewById(R.id.dateTimeContent);
        datetimeView.setText(datetime);
    }
}
