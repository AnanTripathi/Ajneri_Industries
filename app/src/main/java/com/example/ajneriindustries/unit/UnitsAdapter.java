package com.example.ajneriindustries.unit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ajneriindustries.MainActivity;
import com.example.ajneriindustries.R;
import com.example.ajneriindustries.unit.worker.Worker;
import com.example.ajneriindustries.unit.worker.WorkerActivity;

import java.util.ArrayList;
import java.util.Map;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.ViewHolder> {
    private ArrayList<String> unitNameList;
    private ArrayList<String> unitKey;
    private ArrayList<Worker> workers;
    private Context context;
    private static final String TAG = "UnitsAdapter";

    public UnitsAdapter(ArrayList<String> unitNameList,ArrayList<String> unitKey,ArrayList<Worker> workers, Context context) {
        this.unitNameList = unitNameList;
        this.context = context;
        this.unitKey=unitKey;
        this.workers=workers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.unitName.setText(unitNameList.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "The view clicked is="+unitNameList.get(position), Toast.LENGTH_SHORT).show();
                Intent i= new Intent(context, WorkerActivity.class);
                i.putExtra("unit_key",unitKey.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView unitName;
    LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.parent_view);
            unitName=itemView.findViewById(R.id.unit_list_item);
        }
    }
}
