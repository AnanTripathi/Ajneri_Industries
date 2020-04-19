package com.example.ajneriindustries.unit.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ajneriindustries.R;

public class WorkerAdd extends AppCompatActivity {
    private ImageButton addjoiningdate;
    private ImageButton addleavingdate;
    private EditText nameedittext;
    private TextView joiningdatetextView;
    private TextView leavingdatetextView;
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
    }
}
