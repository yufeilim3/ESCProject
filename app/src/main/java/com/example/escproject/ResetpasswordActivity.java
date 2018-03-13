package com.example.escproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResetpasswordActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    TextInputEditText emailText;
    Button resetpasswordButton;
    TextView backLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.input_email);
        resetpasswordButton = findViewById(R.id.btn_resetpassword);
        backLink =findViewById(R.id.link_back);

        /* Execute when back link pressed, goes to the login page */
        resetpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        /* Execute when back link pressed, goes to the login page */
        backLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void reset() {
        if (!validate()) {
            resetpasswordButton.setEnabled(true);
            return;
        }

        // prevent user from resetting again
        resetpasswordButton.setEnabled(false);

        // shown to user while sending email
        final ProgressDialog progressDialog = new ProgressDialog(ResetpasswordActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Sending email...");
        progressDialog.show();

        String email = emailText.getText().toString();

        // Login authentication
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ResetpasswordActivity.this, "Please check your email for instructions to reset your password", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ResetpasswordActivity.this, "Failed to send email to reset password", Toast.LENGTH_LONG).show();
                }
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        resetpasswordButton.setEnabled(true);
                        finish();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    /* Check if email is of correct form */
    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Invalid email address");
            valid = false;
        }

        return valid;
    }
}
