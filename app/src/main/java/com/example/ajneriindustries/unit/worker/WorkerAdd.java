package com.example.ajneriindustries.unit.worker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajneriindustries.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerAdd extends AppCompatActivity {
    private ImageButton addjoiningdate;
    private ImageButton addleavingdate;
    private EditText nameedittext;
    private TextView joiningdatetextView;
    private TextView leavingdatetextView;
    private DatabaseReference currentWorkerReference;
    private Button saveWorker;
    String workerId;
    private View.OnClickListener onClickListener;
    private Worker newWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_add);
        addjoiningdate=findViewById(R.id.add_joining_date);
        addleavingdate=findViewById(R.id.add_leaving_date);
        nameedittext=findViewById(R.id.name_edit_text);
        joiningdatetextView=findViewById(R.id.joining_date_text_view);
        leavingdatetextView=findViewById(R.id.leaving_date_text_view);
        addjoiningdate.setBackgroundResource(R.color.trans);
        addleavingdate.setBackgroundResource(R.color.trans);
        saveWorker=findViewById(R.id.save_worker);
        recieveIntent();
        createClickListener();
        newWorker=new Worker(null,null,null,null,workerId);
        saveWorker.setOnClickListener(onClickListener);
        addjoiningdate.setOnClickListener(onClickListener);
        addleavingdate.setOnClickListener(onClickListener);

    }

    public void createClickListener(){
        onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId())
                {
                    case R.id.save_worker:
                        newWorker.setName(nameedittext.getText().toString());
                        currentWorkerReference.setValue(newWorker);
                        finish();
                    case R.id.add_joining_date:
                        MaterialDatePicker.Builder builder=MaterialDatePicker.Builder.datePicker();
                        builder.setTitleText("Select Joining Date");
                        final MaterialDatePicker materialDatePicker=builder.build();
                        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                                long l=Long.parseLong(selection.toString());
                                java.util.Date d= new java.util.Date(l);
                                Date joiningdate=new Date(d.getDate(),d.getMonth()+1,d.getYear()+1900);
                                joiningdatetextView.setText(joiningdate.toString());
                                newWorker.setJoiningDate(joiningdate);
                            }
                        });
                        materialDatePicker.show(getSupportFragmentManager(),"Date_Picker");
                        break;
                    case R.id.add_leaving_date:
                        MaterialDatePicker.Builder builder1=MaterialDatePicker.Builder.datePicker();
                        builder1.setTitleText("Select Leaving Date");
                        final MaterialDatePicker materialDatePicker1=builder1.build();
                        materialDatePicker1.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                                long l=Long.parseLong(selection.toString());
                                java.util.Date d= new java.util.Date(l);
                                Date leavingdate=new Date(d.getDate(),d.getMonth()+1,d.getYear()+1900);
                                leavingdatetextView.setText(leavingdate.toString());
                                newWorker.setLeavingDate(leavingdate);
                            }
                        });
                        materialDatePicker1.show(getSupportFragmentManager(),"Date_Picker");
                        break;
                }
            }
        };
    }

    public void recieveIntent() {
        workerId=getIntent().getStringExtra("worker_id");
        String s=getIntent().getStringExtra("unit_key");
        currentWorkerReference= FirebaseDatabase.getInstance().getReference().child(getResources().getString(R.string.worker_reference)).child(s).child(workerId);
    }


}
