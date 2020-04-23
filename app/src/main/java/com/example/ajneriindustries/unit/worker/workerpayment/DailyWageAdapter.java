package com.example.ajneriindustries.unit.worker.workerpayment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajneriindustries.R;

import java.util.ArrayList;

public  class DailyWageAdapter extends RecyclerView.Adapter<DailyWageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> dailyWageID;
    private ArrayList<DailyWage> dailyWageArrayList;

    public DailyWageAdapter(Context context, ArrayList<String> dailyWageID, ArrayList<DailyWage> dailyWageArrayList) {
        this.context = context;
        this.dailyWageID = dailyWageID;
        this.dailyWageArrayList = dailyWageArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateOfWork.setText(dailyWageArrayList.get(position).getDate().toString());
        holder.wagePaid.setText(String.valueOf(dailyWageArrayList.get(position).getWagePaid()));
        holder.totalWage.setText(String.valueOf(dailyWageArrayList.get(position).getTotalWage()));
    }

    @Override
    public int getItemCount() {
        return dailyWageID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parent;
        TextView dateOfWork;
        TextView wagePaid;
        TextView totalWage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent_payment_item);
            dateOfWork=itemView.findViewById(R.id.date_of_work);
            wagePaid=itemView.findViewById(R.id.wage_paid);
            totalWage=itemView.findViewById(R.id.total_wage);

        }
    }
}

