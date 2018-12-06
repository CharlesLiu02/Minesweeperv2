package com.example.minesweeperv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MinesweeperFragment extends Fragment {
    private BoardPixelGridView board;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create options menu
        setHasOptionsMenu(true);

        //inflate view
        View rootView = inflater.inflate(R.layout.fragment_minesweeper, container, false);
        board = rootView.findViewById(R.id.boardPixelGridView);

        //receives information from StartScreenFragment about difficulty value
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String difficulty = sharedPref.getString(StartScreenFragment.KEY, "Easy");
        //depending on difficulty, sets size of the board
        if(difficulty.equals("Easy")) {
            board.setSize(10, 10);
        }
        else if(difficulty.equals("Medium")){
            board.setSize(12, 12);
        }
        else{
            board.setSize(15, 15);
        }
        return rootView;
    }

    //add options to options menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add("Home");
    }

    //when item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //add switch case for multiple options
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.container_main, new StartScreenFragment()).commit();

        return true;
    }
}
