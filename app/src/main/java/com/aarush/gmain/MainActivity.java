package com.aarush.gmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signIn;
    private Button signUp;
    private Button Register_with_Phone_Number;
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private CheckBox buttonStaySignedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        signIn =  findViewById(R.id.buttonLogin);
        signUp =  findViewById(R.id.sign_up);
        Register_with_Phone_Number =  findViewById(R.id.register_with_phone_number);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        editTextEmail =  findViewById(R.id.editEmail);
        editTextPassword =  findViewById(R.id.editPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
        findViewById(R.id.register_with_phone_number).setOnClickListener(this);





    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(this,HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    private void userlogin() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        if (email.isEmpty()) {
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
            //stop the function executing further
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            //password is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            editTextPassword.requestFocus();
            //stop the function executing further
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        //if validation are ok
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                userlogin();
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