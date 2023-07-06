package com.example.complaintappusim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {


    EditText usernameInput;
    EditText passwordInput;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        usernameInput = findViewById(R.id.adminusername);
        passwordInput = findViewById(R.id.adminpassword);
        loginButton = findViewById(R.id.login_button);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (!validateUsername() | !validatePassword()) {

                    Toast.makeText(AdminLogin.this, "please fill in your credentials", Toast.LENGTH_SHORT).show();
                } else {
                    adminlogin();
                }
            }
        });
    }

    private boolean validatePassword() {
        String val = usernameInput.getText().toString();
        if (val.isEmpty()) {
            usernameInput.setError("Username cannot be empty");
            return false;
        } else {
            usernameInput.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = passwordInput.getText().toString();
        if (val.isEmpty()) {
            passwordInput.setError("Username cannot be empty");
            return false;
        } else {
            passwordInput.setError(null);
            return true;
        }

    }

    private void adminlogin() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (username.equals("adminusim") && password.equals("123@123")) {
            Toast.makeText(AdminLogin.this, "Login successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AdminLogin.this, AdminRetrieve.class);
            startActivity(intent);

        }else{
            Toast.makeText(AdminLogin.this, "Incorrect username or password.", Toast.LENGTH_SHORT).show();
        }

    }
}