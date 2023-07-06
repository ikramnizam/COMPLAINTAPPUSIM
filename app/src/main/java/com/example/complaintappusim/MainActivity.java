package com.example.complaintappusim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {


    Button  complaint_button,review;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        complaint_button = findViewById(R.id.complaintbutton);
        review = findViewById(R.id.reviewbutton);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            email = bundle.getString("email");

        }




        complaint_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Add_Complaint.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, retrieveData.class);
                intent.putExtra("email", email);

                startActivity(intent);
            }
        });

    }
}