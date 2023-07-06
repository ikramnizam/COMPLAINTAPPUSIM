package com.example.complaintappusim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc2, detailTitle2, detailStatus;
    ImageView detailImage2;
    FloatingActionButton deleteButton;
    String key = "";
    String imageUrl = "";
    String status = "Status: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        detailDesc2 = findViewById(R.id.detailDesc2);
        detailImage2 = findViewById(R.id.detailImage2);
        detailTitle2 = findViewById(R.id.detailTitle2);
        deleteButton = findViewById(R.id.deleteButton);
        detailStatus = findViewById(R.id.status);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc2.setText(bundle.getString("Description"));
            detailTitle2.setText(bundle.getString("Title"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            status = status + bundle.getString("status");
            detailStatus.setText(status);
            Glide.with(this).load(bundle.getString("Image")).into(detailImage2);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Complaint");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), retrieveData.class));
                        finish();
                    }

                });
            }
        });

    }

}