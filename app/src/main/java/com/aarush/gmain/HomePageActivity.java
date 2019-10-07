package com.aarush.gmain;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonNewRegistrationForm;
    private Button buttonEnquiryForm;
    private Button buttonAccountManagement;
    private Button buttonCalenderManagement;
    private Button buttonMembershipManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        buttonNewRegistrationForm =  findViewById(R.id.buttonNewRegistrationForm);
        buttonEnquiryForm =  findViewById(R.id.buttonNewEnquiryForm);
        buttonAccountManagement =  findViewById(R.id.buttonAccountManagement);
        buttonCalenderManagement =  findViewById(R.id.buttonCalenderManagement);
        buttonMembershipManagement =  findViewById(R.id.buttonMembershipManagement);
        findViewById(R.id.buttonCalenderManagement).setOnClickListener(this);
        findViewById(R.id.buttonNewEnquiryForm).setOnClickListener(this);
        findViewById(R.id.buttonNewRegistrationForm).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonNewRegistrationForm:
                startActivity(new Intent(this,NewRegistrationFormActivity.class));
                break;
            case R.id.buttonCalenderManagement:
                startActivity(new Intent(this,Calendermap.class));
                break;
            case R.id.buttonNewEnquiryForm:
                startActivity(new Intent(this,enquiryform.class));
                break;
        }

    }
}