package com.example.minesweeperv2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EndScreenDialog extends DialogFragment {

    //add needed instance variables
    private TextView textView;

    public EndScreenDialog(){
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EndScreenDialog newInstance(String title){
        EndScreenDialog frag = new EndScreenDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        textView = view.findViewById(R.id.textView);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title");
        getDialog().setTitle(title);
        // request focus to field
        textView.requestFocus();
    }
}
