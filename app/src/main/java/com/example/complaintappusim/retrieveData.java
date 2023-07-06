package com.example.complaintappusim;

import static com.example.complaintappusim.R.id.add_complaint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class retrieveData extends AppCompatActivity {


    DatabaseReference databaseReference;
    Button addcomplaint;
    RecyclerView recyclerView;
    List<ComplaintClass> dataList;
    MyAdapter adapter;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        recyclerView = findViewById(R.id.recyclerView);
        addcomplaint = findViewById(add_complaint);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            email = bundle.getString("email");
        }


        addcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(retrieveData.this, Add_Complaint.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(retrieveData.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(retrieveData.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        adapter = new MyAdapter(retrieveData.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Complaint");
        Query query = databaseReference.orderByChild("email").equalTo(email);
        
        dialog.show();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot snap : snapshot.getChildren()){
                    ComplaintClass complaintClass = snap.getValue(ComplaintClass.class);

                    complaintClass.setKey(snap.getKey());
                    dataList.add(complaintClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();

            }
        });
//         databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dataList.clear();
//                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
//                    ComplaintClass complaintClass = itemSnapshot.getValue(ComplaintClass.class);
//
//                    complaintClass.setKey(itemSnapshot.getKey());
//                    dataList.add(complaintClass);
//                }
//                adapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                dialog.dismiss();
//            }
//        });


    }

}