package com.example.ajneriindustries.unit.worker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.ajneriindustries.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkerActivity extends AppCompatActivity {
    private DatabaseReference workerReference;
    private String unitkey;
    private FloatingActionButton addWorker;
    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private ArrayList<String> workerID=new ArrayList<>();
    private ArrayList<Worker> workerList=new ArrayList<>();
    private ChildEventListener childEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        intentReceiver();
        initiatechild();
        workerReference.addChildEventListener(childEventListener);
        addWorker=findViewById(R.id.floatingActionButton2);
        addWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WorkerActivity.this,WorkerAdd.class);
                i.putExtra("worker_id",unitkey+"worker"+(workerID.size()+1));
                i.putExtra("unit_key",unitkey);
                startActivity(i);

            }
        });

    }
    public void intentReceiver(){
        workerReference=FirebaseDatabase.getInstance().getReference().child(getResources().getString(R.string.worker_reference));
        unitkey=getIntent().getStringExtra("unit_key");
        workerReference=workerReference.child(unitkey);
        Toast.makeText(this, "the object clicked has id="+unitkey, Toast.LENGTH_SHORT).show();
    }
    private void initiatechild()
    {
        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                workerID.add(dataSnapshot.getKey());
                Worker w=dataSnapshot.getValue(Worker.class);
                workerList.add(w);
                if(workerID.size()!=1){
                adapter.notifyItemInserted(workerID.size()-1);}
                addAdapter();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }
    private void addAdapter()
    {
        if(workerID.size()==1){
        recyclerView=findViewById(R.id.worker_list_recyclerview);
        adapter=new WorkerAdapter(WorkerActivity.this,workerID,workerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));}
    }
}
