package com.example.calculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class FragmentDialog extends DialogFragment {
    ArrayList<String>list;
    public FragmentDialog(ArrayList<String> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Steps");
        String[]str=new String[list.size()];
        for(int i=0;i<list.size();i++){
            str[i]=list.get(i);
        }
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), list.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}
