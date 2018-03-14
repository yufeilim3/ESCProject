package com.example.escproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    
    TextInputEditText nameText;
    TextInputEditText emailText;
    TextInputEditText passwordText;
    TextInputEditText reenterPasswordText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button signupButton;
    TextView loginLink;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nameText = findViewById(R.id.input_name);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        reenterPasswordText = findViewById(R.id.input_reenterPassword);
        radioGroup = (RadioGroup)findViewById(R.id.rgroup_signup);
        signupButton = findViewById(R.id.btn_signup);
        loginLink = findViewById(R.id.link_login);
        
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        /* Execute when login link pressed, goes to the login page */
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void radioRole(View view){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        Toast.makeText(this, radioButton.getText() + " selected",Toast.LENGTH_SHORT).show();
    }

    public void saveUserInformation() {
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        FirebaseUser firebaseUser = auth.getCurrentUser();
        assert firebaseUser != null;

        if (radioButton.getText().equals("Student")) {
            Student student = new Student(firebaseUser.getUid(), name);
            databaseReference.child("users").child("Student").child(firebaseUser.getUid()).setValue(student);
        } else if (radioButton.getText().equals("Instructor")) {
            Instructor instructor = new Instructor(firebaseUser.getUid(), name);
            databaseReference.child("users").child("Instructor").child(firebaseUser.getUid()).setValue(instructor);
        }
    }
    /* Execute when signup button pressed + Check information given */
    public void signup() {
        if (!validate()) {
            signupButton.setEnabled(true);
            return;
        }
        
        // prevent user from signing up again
        signupButton.setEnabled(false);
        
        // shown to user while creating their account
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String reenterPassword = reenterPasswordText.getText().toString();

        // Create user account
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else {
                    saveUserInformation();
                    progressDialog.dismiss();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Account Created", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        signupButton.setEnabled(true);
                    }
                }, 3000);
    }
    
    /* Check if email is of correct form && password is of correct length */
    public boolean validate() {
        boolean valid = true;
        
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String reenterPassword = reenterPasswordText.getText().toString();
        int radioID = radioGroup.getCheckedRadioButtonId();

        if (name.isEmpty()) {
            nameText.setError("Enter your name");
            valid = false;
        }
        
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Invalid email address");
            valid = false;
        }
        
        if (password.isEmpty() || password.length() < 6 || password.length() > 10) {
            passwordText.setError("Invalid password");
            valid = false;
        }
        
        if (reenterPassword.isEmpty() || reenterPassword.length() < 6 || reenterPassword.length() > 10) {
            reenterPasswordText.setError("Invalid password");
            valid = false;
        }
        else if (!reenterPassword.equals(password)) {
            reenterPasswordText.setError("Password do not match");
            valid = false;
        }

        if (radioID == -1){
            Toast.makeText(this, "Please indicate Student or Instructor", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
}