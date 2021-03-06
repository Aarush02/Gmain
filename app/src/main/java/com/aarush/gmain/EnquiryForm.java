package com.aarush.gmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnquiryForm extends AppCompatActivity implements View.OnClickListener {

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
        buttonEnquiry =  findViewById(R.id.buttonEnquiryRegister);
        editTextEnquiryUsername =  findViewById(R.id.enquiry_form_username);
        editTextEnquiryPnoneNumber =  findViewById(R.id.enquiry_form_phone_number);
        editTextEnquiryEmail =  findViewById(R.id.enquiry_form_email);
        editTextEnquiryGoal =  findViewById(R.id.enquiry_form_goal);
        editTextEnquiryGimmingExperiance =  findViewById(R.id.enquiry_form_gyming_experience);
        editTextEnquiryAddress =  findViewById(R.id.enquiry_form_address);
        findViewById(R.id.buttonEnquiryRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.buttonEnquiryRegister){

                addEnquiryForm();

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

