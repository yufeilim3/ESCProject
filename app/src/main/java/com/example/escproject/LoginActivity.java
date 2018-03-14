package com.example.escproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth auth;

    TextInputEditText emailText;
    TextInputEditText passwordText;
    Button loginButton;
    TextView signupLink;
    TextView resetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        loginButton = findViewById(R.id.btn_login);
        signupLink = findViewById(R.id.link_signup);
        resetpassword = findViewById(R.id.link_resetpassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        /* Execute when resetpassword link pressed, goes to the resetpassword page */
        resetpassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), ResetpasswordActivity.class);
                 startActivity(intent);
                 finish();
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

        new loginTask().execute();

    }

    ProgressDialog progressDialog = null;

    // shown to user while authenticating the email and password
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    class loginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            // Login authentication
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(LoginActivity.this, CourseActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            if (LoginActivity.this.isDestroyed()) {
                return;
            }
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            loginButton.setEnabled(true);
                            finish();
                            dismissProgressDialog();
                        }
                    }, 3000);

        }
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