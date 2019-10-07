package com.aarush.gmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewRegistrationFormActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private DatabaseReference databaseReference;
    private EditText editTextRegistrationName;
    private EditText editTextRegistrationWeight;
    private EditText editTextRegistrationPhoneNumber;
    private EditText editTextRegistrationEmail;
    private EditText editTextRegistrationGoal;
    private EditText editTextRegistrationGymingExperiance;
    private EditText editTextRegistrationAddress;
    private EditText editTextRegistrationMedicalProblem;
    private EditText editTextRegistrationCurrentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration_form);
        databaseReference = FirebaseDatabase.getInstance().getReference("Registration Form");
        buttonRegister =  findViewById(R.id.buttonNewRegistration);
        editTextRegistrationName =  findViewById(R.id.newRegistrationFormName);
        editTextRegistrationWeight =  findViewById(R.id.newRegistrationFormWeight);
        editTextRegistrationPhoneNumber =  findViewById(R.id.newRegistrationFormPhoneNumber);
        editTextRegistrationEmail =  findViewById(R.id.newRegistrationFormEmail);
        editTextRegistrationGoal =  findViewById(R.id.newRegistrationFormGoal);
        editTextRegistrationGymingExperiance =  findViewById(R.id.newRegistrationFormGymingExperiance);
        editTextRegistrationAddress =  findViewById(R.id.newRegistrationFormAddress);
        editTextRegistrationMedicalProblem =  findViewById(R.id.newRegistrationFormMedicalProblem);
        editTextRegistrationCurrentAddress = findViewById(R.id.newRegistrationFormCurrentAddress);
        findViewById(R.id.buttonNewRegistration).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonNewRegistration:
                addEnquiryForm();
                break;
        }

    }
    private void addEnquiryForm(){
        String Name= editTextRegistrationName.getText().toString().trim();
        String Weight= editTextRegistrationWeight.getText().toString().trim();
        String Phone= editTextRegistrationPhoneNumber.getText().toString().trim();
        String Email= editTextRegistrationEmail.getText().toString().trim();
        String Goal= editTextRegistrationGoal.getText().toString().trim();
        String GymingExperiance= editTextRegistrationGymingExperiance.getText().toString().trim();
        String Address= editTextRegistrationAddress.getText().toString().trim();
        String MedicalProblem= editTextRegistrationMedicalProblem.getText().toString().trim();
        String CurrentAddress= editTextRegistrationCurrentAddress.getText().toString().trim();
        if(!TextUtils.isEmpty(Name)&&!TextUtils.isEmpty(Weight)&&!TextUtils.isEmpty(Phone)&&!TextUtils.isEmpty(Email)&&
                !TextUtils.isEmpty(Goal)&&!TextUtils.isEmpty(GymingExperiance)&&!TextUtils.isEmpty(Address)&&
                !TextUtils.isEmpty(MedicalProblem)&&!TextUtils.isEmpty(CurrentAddress)){
            String id = databaseReference.push().getKey();
            Registration name1=new Registration(Name,Weight,Phone,Email,Goal,GymingExperiance,Address,MedicalProblem,CurrentAddress);
            databaseReference.child(id).setValue(name1);
            Toast.makeText(this,"Registration Form Added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter the name",Toast.LENGTH_LONG).show();
        }
    }
}
