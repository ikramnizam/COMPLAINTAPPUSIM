package com.example.complaintappusim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class AdminDetail extends AppCompatActivity {

    TextView detailDesc, detailTitle;
    Button receiveButton, rejectButton;
    ImageView detailImage;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);
        
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        receiveButton = findViewById(R.id.receivebtn);
        rejectButton = findViewById(R.id.rejectbtn);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        DatabaseReference complaintRef = FirebaseDatabase.getInstance().getReference("Complaint");
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaintRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Map<String, Object> update = new HashMap<>();
                            update.put("status", "Approved");
                            complaintRef.child(key).updateChildren(update);
                            Toast.makeText(AdminDetail.this, "the complaint have been received ", Toast.LENGTH_SHORT).show();
//                sendStatusUpdate("received", key);
                            Intent intent = new Intent(AdminDetail.this, AdminRetrieve.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                showMessage();
//                loadInfo();
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaintRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Map<String, Object> update = new HashMap<>();
                            update.put("status", "Rejected");
                            complaintRef.child(key).updateChildren(update);
                            Toast.makeText(AdminDetail.this, "the complaint have been rejected ", Toast.LENGTH_SHORT).show();

//                sendStatusUpdate("received", key);
                            Intent intent = new Intent(AdminDetail.this, AdminRetrieve.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                sendStatusUpdate("rejected", key);
                Intent intent = new Intent(AdminDetail.this, AdminRetrieve.class);
                startActivity(intent);
//                showMessageReject();
//                loadInfo();
            }
        });



    }

//    private void sendStatusUpdate(String received, String key) {
//        class AdminManager {
//            private DatabaseReference mDatabase;
//
//            public AdminManager() {
//                mDatabase = FirebaseDatabase.getInstance().getReference();
//            }
//
//            public void sendStatusUpdate(String status, String key) {
//                mDatabase.child("complaints").child(key).child("status").setValue(status);
//            }
//        }
//
//    }

//    private void loadInfo(){
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//            detailDesc.setText(bundle.getString("Description"));
//            detailTitle.setText(bundle.getString("Title"));
//            key = bundle.getString("Key");
//            imageUrl = bundle.getString("Image");
//            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
//        }
//    }

//    private void showMessageReject() {
//
//        String message = "your complaint have been rejected";
//        Intent intent = new Intent(AdminDetail.this, DetailActivity.class);
//        intent.putExtra("message", message);
//        startActivity(intent);
//
//    }
//
//    private void showMessage() {
//        String message = "your complaint have been received";
//        Intent intent = new Intent(AdminDetail.this, DetailActivity.class);
//        intent.putExtra("message", message);
//        startActivity(intent);
//    }

}