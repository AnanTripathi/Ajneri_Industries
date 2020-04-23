package com.example.ajneriindustries.unit.worker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajneriindustries.R;
import com.example.ajneriindustries.unit.worker.workerpayment.WorkerPaymentActivity;

import java.util.ArrayList;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> workerID;
    private ArrayList<Worker> workersList;

    public WorkerAdapter(Context context, ArrayList<String> workerID, ArrayList<Worker> workersList) {
        this.context = context;
        this.workerID = workerID;
        this.workersList = workersList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.workerName.setText(workersList.get(position).getName());
        if(workersList.get(position).getJoiningDate()==null)
        {
        holder.joindateLayout.setVisibility(View.GONE);
        }
        else
            {
                holder.joindateTextView.setText(workersList.get(position).getJoiningDate().toString());
            }
        if(workersList.get(position).getLeavingDate()==null)
        {
            holder.leavingdateLayout.setVisibility(View.GONE);
        }
        else
            {
                holder.leavingdatetextView.setText(workersList.get(position).getLeavingDate().toString());
            }
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, WorkerPaymentActivity.class);
                i.putExtra("worker_Id",workerID.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workerID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout parentView;
        TextView workerName;
        LinearLayout joindateLayout;
        LinearLayout leavingdateLayout;
        TextView joindateTextView;
        TextView leavingdatetextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView=itemView.findViewById(R.id.worker_item_layout);
            workerName= itemView.findViewById(R.id.worker_name);
            joindateLayout=itemView.findViewById(R.id.joining_date_layout);
            leavingdateLayout=itemView.findViewById(R.id.leaving_date_layout);
            joindateTextView=itemView.findViewById(R.id.joindate_text_view);
            leavingdatetextView=itemView.findViewById(R.id.leaving_text_view);
        }
    }
}
