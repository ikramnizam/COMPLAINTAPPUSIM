package com.example.complaintappusim;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.MessageDigest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);






        
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateUsername() | !validatePassword() | !validateEmail() |!validateName()) {
                    Toast.makeText(register.this, "please fill all your credentials", Toast.LENGTH_SHORT).show();
                } else {
                    registeruser();
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }


    private boolean validateName() {
        String val = signupName.getText().toString();
        if (val.isEmpty()) {
            signupName.setError("please insert your name");
            return false;
        } else {
            signupName.setError(null);
            return true;
        }
    }


    private boolean validateEmail() {
        String val = signupEmail.getText().toString();
        if (val.isEmpty()) {
            signupEmail.setError("please insert your email");
            return false;
        } else {
            signupEmail.setError(null);
            return true;
        }
    }



    private boolean validatePassword() {
        String val = signupPassword.getText().toString();
        if (val.isEmpty()) {
            signupPassword.setError("please create your password");
            return false;
        } else {
            signupPassword.setError(null);
            return true;
        }
    }


    private boolean validateUsername() {
        String val = signupUsername.getText().toString();
        if (val.isEmpty()) {
            signupUsername.setError("please enter your username");
            return false;
        } else {
            signupUsername.setError(null);
            return true;
        }
    }






    private void registeruser() {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        String name = signupName.getText().toString();
        String email = signupEmail.getText().toString();
        String username = signupUsername.getText().toString();
        String password = signupPassword.getText().toString();
        String encryptedPassword = encryptPassword(password);

        HelperClass helperClass = new HelperClass(name, email, username, encryptedPassword);
        reference.child(username).setValue(helperClass);


        Toast.makeText(register.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);

    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
//password plaintext
//password validation
//hashing login
//admin
//sequence diagram
//
//khamis 12 pm