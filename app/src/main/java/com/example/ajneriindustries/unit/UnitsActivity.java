package com.example.ajneriindustries.unit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ajneriindustries.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UnitsActivity extends AppCompatActivity implements AddUnitDialog.AddUnitDialogListener {
    private static final String TAG = "Units";
    private DatabaseReference unitsReference;
    private UnitsAdapter adapter;
    private FloatingActionButton addUnitsButton;
    private ArrayList<String> unitsNameList=new ArrayList<>();
    private ArrayList<String> unitKeyList=new ArrayList<>();
    private ChildEventListener unitChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        unitsReference=FirebaseDatabase.getInstance().getReference().child(getResources().getString(R.string.units_reference));
        addUnitsButton=findViewById(R.id.floatingActionButton);
        unitChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String currentUnitName=dataSnapshot.getValue(String.class);
                String key=dataSnapshot.getKey();
                unitsNameList.add(currentUnitName);
                unitKeyList.add(key);
                if(unitsNameList.size()!=1){
                adapter.notifyItemInserted(unitsNameList.size()-1);}
                Toast.makeText(UnitsActivity.this, currentUnitName, Toast.LENGTH_SHORT).show();
                initRecyclerView();
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
        unitsReference.addChildEventListener(unitChildEventListener);
        addUnitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        AddUnitDialog addUnitDialog=new AddUnitDialog();
        addUnitDialog.show(getSupportFragmentManager(),"add unit dialog");
    }

    private void initRecyclerView(){
        if(unitsNameList.size()==1)
        {
            RecyclerView recyclerView=findViewById(R.id.unit_recyclerview);
            adapter=new UnitsAdapter(unitsNameList,unitKeyList,null,UnitsActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void saveTheUnit(String unitNameString) {
        unitsReference.child(Integer.toString(unitsNameList.size()+1)).setValue(unitNameString);
    }

}
