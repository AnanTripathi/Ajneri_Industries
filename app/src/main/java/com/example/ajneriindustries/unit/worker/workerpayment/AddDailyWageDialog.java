package com.example.ajneriindustries.unit.worker.workerpayment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ajneriindustries.R;
import com.example.ajneriindustries.unit.worker.Date;

import java.util.Calendar;
import java.util.TimeZone;

public class AddDailyWageDialog extends AppCompatDialogFragment {
    private DatePicker datePicker;
    private EditText totalWageEditText;
    private EditText wagePaidEditText;
    private AddDailyWageDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.payment_add_dialog,null);
        builder.setView(view)
                .setTitle("Add Dialy Wage")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer wapaid=Integer.parseInt(wagePaidEditText.getText().toString());
                        Integer totalWage=Integer.parseInt(totalWageEditText.getText().toString());
                        Date d=new Date(datePicker.getDayOfMonth(),datePicker.getMonth()+1,datePicker.getYear());
                        DailyWage dailyWage=new DailyWage(wapaid,totalWage,d);
                        listener.saveTheWage(dailyWage);
                    }
                });
        datePicker=view.findViewById(R.id.date_of_work);
        totalWageEditText=view.findViewById(R.id.total_wage_editText);
        wagePaidEditText=view.findViewById(R.id.wage_paid_editText);
        return builder.create();
    }
    public interface  AddDailyWageDialogListener{
        void saveTheWage(DailyWage dailyWage);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
          listener=(AddDailyWageDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement AddDailyWageDialogListener");
        }
    }
}
