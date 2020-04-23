package com.example.ajneriindustries.unit.worker.workerpayment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajneriindustries.R;
import com.example.ajneriindustries.unit.worker.Date;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WorkerPaymentActivity extends AppCompatActivity implements AddDailyWageDialog.AddDailyWageDialogListener {
    private String workerID;
    private ArrayList<DailyWage> dailyWageArrayList=new ArrayList<>();
    private ArrayList<String> wageKeyList=new ArrayList<>();
    private DatabaseReference wageReference;
    private FloatingActionButton addworkerpayment;
    private DailyWageAdapter adapter;
    private ChildEventListener dailyWageChildEventListener;
    private Integer totalWagePaidSum=0;
    private Integer totalWageSum=0;
    private TextView totalWagePaidTextView;
    private TextView totalWageTextView;
    private EditText fromDateEditText;
    private EditText fromMonthEditText;
    private EditText fromYearEditText;
    private EditText toDateEditText;
    private EditText toMonthEditText;
    private EditText toYearEditText;
    private Button searchFromToButton;
    ArrayList<String> rangeWorkerKeyList;
    ArrayList<DailyWage> rangeDailyWageArrayList;
    RecyclerView recyclerView;
    private LinearLayout wagetobepaidlayout;
    private LinearLayout moneytobetakenbacklayout;
    private TextView wagetobepaidtextview;
    private TextView moneytobetakentextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_payment);
        wagetobepaidlayout=findViewById(R.id.wage_to_be_paid_layout);
        moneytobetakenbacklayout=findViewById(R.id.money_to_be_taken_back_layout);
        wagetobepaidtextview=findViewById(R.id.wage_to_be_paid_textview);
        moneytobetakentextview=findViewById(R.id.money_to_be_taken_back_textview);
        addworkerpayment=findViewById(R.id.floatingActionButtonAddWorkerPayment);
        totalWagePaidTextView=findViewById(R.id.total_wage_paid_textview);
        totalWageTextView=findViewById(R.id.total_wage_textview);
        fromDateEditText=findViewById(R.id.from_date_edittext);
        fromMonthEditText=findViewById(R.id.from_month_editext);
        fromYearEditText=findViewById(R.id.from_year_editext);
        toDateEditText=findViewById(R.id.to_day_editext);
        toYearEditText=findViewById(R.id.to_year_editext);
        toMonthEditText=findViewById(R.id.to_month_editext);
        searchFromToButton=findViewById(R.id.search_from_to_button);
        wagetobepaidlayout.setVisibility(View.GONE);
        moneytobetakenbacklayout.setVisibility(View.GONE);
        recieveInstance();

        wageReference=FirebaseDatabase.getInstance().getReference().child(getResources().getString(R.string.wage_reference)).child(workerID);
        addworkerpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDailyWageDialog addDailyWageDialog=new AddDailyWageDialog();
                addDailyWageDialog.show(getSupportFragmentManager(),"add wage");
            }
        });
        dailyWageChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                wageKeyList.add(dataSnapshot.getKey());
                dailyWageArrayList.add(dataSnapshot.getValue(DailyWage.class));
                totalWageSum+=dailyWageArrayList.get(dailyWageArrayList.size()-1).getTotalWage();
                totalWagePaidSum+=dailyWageArrayList.get(dailyWageArrayList.size()-1).getWagePaid();
                totalWagePaidTextView.setText(String.valueOf(totalWagePaidSum));
                totalWageTextView.setText(String.valueOf(totalWageSum));
                if(dailyWageArrayList.size()!=1){
                    adapter.notifyItemInserted(dailyWageArrayList.size()-1);
                }
                addRecyclerAdapter();
                if(totalWageSum>totalWagePaidSum)
                {   moneytobetakenbacklayout.setVisibility(View.GONE);
                    moneytobetakentextview.setText(null);
                    wagetobepaidlayout.setVisibility(View.VISIBLE);
                    wagetobepaidtextview.setText(String.valueOf(totalWageSum-totalWagePaidSum)+"₹");
                }
                else
                {
                    wagetobepaidlayout.setVisibility(View.GONE);
                    wagetobepaidtextview.setText(null);
                    moneytobetakenbacklayout.setVisibility(View.VISIBLE);
                    moneytobetakentextview.setText(String.valueOf(totalWagePaidSum-totalWageSum)+"₹");
                }
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
        wageReference.addChildEventListener(dailyWageChildEventListener);
        searchFromToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromDateEditText.getText().toString().isEmpty()||fromMonthEditText.getText().toString().isEmpty()||fromYearEditText.getText().toString().isEmpty()||toDateEditText.getText().toString().isEmpty()||toMonthEditText.getText().toString().isEmpty()||toYearEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(WorkerPaymentActivity.this, "Please fill all the date entries", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Date fromDate=new Date(Integer.parseInt(fromDateEditText.getText().toString()),Integer.parseInt(fromMonthEditText.getText().toString()),Integer.parseInt(fromYearEditText.getText().toString()));
                    Date toDate=new Date(Integer.parseInt(toDateEditText.getText().toString()),Integer.parseInt(toMonthEditText.getText().toString()),Integer.parseInt(toYearEditText.getText().toString()));
                    rangeWorkerKeyList=new ArrayList<>();
                    rangeDailyWageArrayList=new ArrayList<>();
                    totalWageSum=0;
                    totalWagePaidSum=0;
                    for(int i=0;i<dailyWageArrayList.size();i++)
                    {
                        if(dailyWageArrayList.get(i).getDate().greaterThenEqualTo(fromDate)&&dailyWageArrayList.get(i).getDate().lessThenEqualTo(toDate)){
                            rangeDailyWageArrayList.add(dailyWageArrayList.get(i));
                            rangeWorkerKeyList.add(wageKeyList.get(i));
                            totalWageSum+=dailyWageArrayList.get(i).getTotalWage();
                            totalWagePaidSum+=dailyWageArrayList.get(i).getWagePaid();
                        }
                    }
                    adapter=new DailyWageAdapter(WorkerPaymentActivity.this,rangeWorkerKeyList,rangeDailyWageArrayList);
                    recyclerView.setAdapter(adapter);
                    totalWagePaidTextView.setText(String.valueOf(totalWagePaidSum));
                    totalWageTextView.setText(String.valueOf(totalWageSum));
                    if(totalWageSum>totalWagePaidSum)
                    {   moneytobetakenbacklayout.setVisibility(View.GONE);
                        moneytobetakentextview.setText(null);
                        wagetobepaidlayout.setVisibility(View.VISIBLE);
                        wagetobepaidtextview.setText(String.valueOf(totalWageSum-totalWagePaidSum)+"₹");
                    }
                    else
                    {
                        wagetobepaidlayout.setVisibility(View.GONE);
                        wagetobepaidtextview.setText(null);
                        moneytobetakenbacklayout.setVisibility(View.VISIBLE);
                        moneytobetakentextview.setText(String.valueOf(totalWagePaidSum-totalWageSum)+"₹");
                    }
                }
            }
        });
    }
    public void recieveInstance()
    {
        workerID=getIntent().getStringExtra("worker_Id");
    }

    @Override
    public void saveTheWage(DailyWage dailyWage) {

        wageReference.child(dailyWage.getDate().toString()).setValue(dailyWage);
    }
    private  void addRecyclerAdapter()
    {
        if(wageKeyList.size()==1){
            recyclerView=findViewById(R.id.payment_recyclerview);
            adapter=new DailyWageAdapter(WorkerPaymentActivity.this,wageKeyList,dailyWageArrayList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}
