package com.aarush.gmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signIn;
    private Button signUp;
    private Button Register_with_Phone_Number;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (Button) findViewById(R.id.sign_up);
        Register_with_Phone_Number = (Button) findViewById(R.id.register_with_phone_number);
        firebaseAuth=FirebaseAuth.getInstance();
        findViewById(R.id.sign_in).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
        findViewById(R.id.register_with_phone_number).setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in:
                startActivity(new Intent(this, Signin.class));
                break;
            case R.id.sign_up:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.register_with_phone_number:
                startActivity(new Intent(this,PhoneNumberRegistration.class));
                break;
        }
    }
}