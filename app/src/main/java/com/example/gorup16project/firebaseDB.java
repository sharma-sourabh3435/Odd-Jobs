package com.example.gorup16project;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseDB {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<jobs> jobs = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<jobs> jobs, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public firebaseDB() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("jobs");
    }

    public void readJobs(final DataStatus dataStatus) {
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobs.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    jobs job = keyNode.getValue(jobs.class);
                    jobs.add(job);
                }
                dataStatus.DataIsLoaded(jobs, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
