package com.example.minesweeperv2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartScreenFragment extends Fragment{

    Button buttonEasy;
    Button buttonMedium;
    Button buttonHard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start_screen, container, false);

        buttonEasy = rootView.findViewById(R.id.button_start_screen_easy);
        buttonMedium = rootView.findViewById(R.id.button_start_screen_medium);
        buttonHard = rootView.findViewById(R.id.button_start_screen_hard);

        //replaces start screen fragment with minesweeper fragment when the button is clicked
        buttonEasy.setOnClickListener(new Listener());
        buttonMedium.setOnClickListener(new Listener());
        buttonHard.setOnClickListener(new Listener());
        return rootView;
    }

    private class Listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //opens minesweeper view
            //replaces container main that holds start screen fragment with minesweeper fragment
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.container_main, new MinesweeperFragment()).commit();
        }
    }

}
