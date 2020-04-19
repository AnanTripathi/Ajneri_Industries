package com.example.ajneriindustries.unit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ajneriindustries.R;

public class AddUnitDialog extends AppCompatDialogFragment {
    private EditText unitName;
    private AddUnitDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.unit_add_dialog,null);
        builder.setView(view)
                .setTitle("Add Unit")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String unitNameString=unitName.getText().toString();
                        listener.saveTheUnit(unitNameString);
                    }
                });
        unitName=view.findViewById(R.id.add_unitname_edittext);
        return  builder.create();
    }
    public interface AddUnitDialogListener{
        void saveTheUnit(String unitnameString);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(AddUnitDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement AddUnitDialogListener");
        }
    }
}
