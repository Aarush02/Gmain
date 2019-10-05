package com.aarush.gmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class enquiryform extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase firebaseDatabase;
    private Button buttonEnquiry;
    private DatabaseReference databaseReference;
    private EditText editTextEnquiryUsername;
    private EditText editTextEnquiryPnoneNumber;
    private EditText editTextEnquiryEmail;
    private EditText editTextEnquiryGoal;
    private EditText editTextEnquiryGimmingExperiance;
    private EditText editTextEnquiryAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiryform);
        databaseReference = FirebaseDatabase.getInstance().getReference("Enquiry Form");
        buttonEnquiry = (Button) findViewById(R.id.buttonEnquiryRegister);
        editTextEnquiryUsername = (EditText) findViewById(R.id.enquiry_form_username);
        editTextEnquiryPnoneNumber = (EditText) findViewById(R.id.enquiry_form_phone_number);
        editTextEnquiryEmail = (EditText) findViewById(R.id.enquiry_form_email);
        editTextEnquiryGoal = (EditText) findViewById(R.id.enquiry_form_goal);
        editTextEnquiryGimmingExperiance = (EditText) findViewById(R.id.enquiry_form_gyming_experience);
        editTextEnquiryAddress = (EditText) findViewById(R.id.enquiry_form_address);
        findViewById(R.id.buttonEnquiryRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonEnquiryRegister:
            {
                addEnquiryForm();
            }
        }
    }
    private void addEnquiryForm(){
        String userName= editTextEnquiryUsername.getText().toString().trim();
        String phoneNumber= editTextEnquiryPnoneNumber.getText().toString().trim();
        String email= editTextEnquiryEmail.getText().toString().trim();
        String goal= editTextEnquiryGoal.getText().toString().trim();
        String gimmingExperiance= editTextEnquiryGimmingExperiance.getText().toString().trim();
        String address= editTextEnquiryAddress.getText().toString().trim();
        if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(phoneNumber)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(goal)&&!TextUtils.isEmpty(gimmingExperiance)&&!TextUtils.isEmpty(address)){
            String id = databaseReference.push().getKey();
            Enquiry name1=new Enquiry(userName,phoneNumber,email,goal,gimmingExperiance,address);
            databaseReference.child(id).setValue(name1);
            Toast.makeText(this,"Enquiry Form Added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter the name",Toast.LENGTH_LONG).show();
        }
    }

    }

