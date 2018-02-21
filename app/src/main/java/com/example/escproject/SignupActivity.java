package com.example.escproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText nameText;
    EditText emailText;
    EditText passwordText;
    EditText reenterPasswordText;
    Button signupButton;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameText = findViewById(R.id.input_name);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        reenterPasswordText = findViewById(R.id.input_reenterPassword);
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

        // TODO: Account creation
        Toast.makeText(getApplicationContext(),"Account Created", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        signupButton.setEnabled(true);
                        finish();
                        progressDialog.dismiss();
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

        return valid;
    }
}
