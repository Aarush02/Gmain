package com.aarush.gmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneNumberRegistration extends AppCompatActivity implements View.OnClickListener{
    EditText editTextPhone,editTextCode;
    Button buttonGetVerificationCode,buttonVerifyCode;
    FirebaseAuth firebaseAuth;
    private String codeSent;
    private PhoneAuthProvider.ForceResendingToken forceResendToken;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_registration);
        editTextPhone=findViewById(R.id.editTextPhoneNumber);
        editTextCode=findViewById(R.id.EditTextenterVerificationCode);
        buttonGetVerificationCode=findViewById(R.id.buttonGetVerificationCode);
        buttonVerifyCode=findViewById(R.id.buttonVerify);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        findViewById(R.id.buttonGetVerificationCode).setOnClickListener(this);
        findViewById(R.id.buttonVerify).setOnClickListener(this);





        findViewById(R.id.buttonGetVerificationCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });


        findViewById(R.id.buttonVerify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyVerificationCode();

            }
        });


    }
    private void verifyVerificationCode(){
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        String code=editTextCode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(PhoneNumberRegistration.this, ProfileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            // ...
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "Incorrect Verification Code", Toast.LENGTH_SHORT).show();
                            }
                                // Sign in failed, display a message and update the UI
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    Toast.makeText(getApplicationContext(), "Incorrect Verification Code", Toast.LENGTH_SHORT).show();
                                    // The verification code entered was invalid
                                }
                            }
                        }
                        });
                    }
    private void sendVerificationCode()
    {
        String phone=editTextPhone.getText().toString();
        if (phone.isEmpty()){
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }
        if (phone.length()<10){
            editTextPhone.setError("Please enter a valid phone number");
            editTextPhone.requestFocus();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Sent Verification Code", Toast.LENGTH_SHORT).show();
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Could not Sent Verification Code", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent=s;
            forceResendToken =forceResendingToken;
            progressDialog.dismiss();

        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonGetVerificationCode:
                sendVerificationCode();
                break;
            case R.id.buttonVerify:
                verifyVerificationCode();
                break;
        }
    }
}
