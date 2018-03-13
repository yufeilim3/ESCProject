package com.example.escproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth auth;

    TextInputEditText emailText;
    TextInputEditText passwordText;
    Button loginButton;
    TextView signupLink;
    TextView resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        loginButton = findViewById(R.id.btn_login);
        signupLink = findViewById(R.id.link_signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        /* Execute when signup link pressed, goes to the signup page */
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /* Execute when login button pressed + Authenticate login */
    public void login() {
        if (!validate()) {
            loginButton.setEnabled(true);
            return;
        }

        // prevent user from logging in again
        loginButton.setEnabled(false);

        // shown to user while authenticating the email and password
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        // TODO: Login authentication
        boolean isEmailMatch = email.equals("john@mymail.sutd.edu.sg");
        boolean isPasswordMatch = password.equals("12345678");
        if (isEmailMatch) {
            Log.i(TAG, "Email matched");
            if (isPasswordMatch) {
                Intent intent = new Intent(getBaseContext(), CourseActivity.class);
                startActivity(intent);
                Log.i(TAG, "Password matched");
            }
            else {
                Log.i(TAG, "Password invalid");
                passwordText.setError("Invalid password");
            }
        }
        else {
            Log.i(TAG, "Email invalid");
            emailText.setError("Invalid email address");
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        loginButton.setEnabled(true);
                        finish();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    /* Check if email is of correct form && password is of correct length */
    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Invalid email address");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 10) {
            passwordText.setError("Invalid password");
            valid = false;
        }

        return valid;
    }

    /* Disable going back to the LoginActivity */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}