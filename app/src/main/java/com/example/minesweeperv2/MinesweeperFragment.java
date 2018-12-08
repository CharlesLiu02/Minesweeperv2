//shows the actual minesweeper game with the board

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

    //creates the view of the fragment
    //basically creates what is to be shown on the fragment screen
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create options menu in the top right of the fragment screen
        setHasOptionsMenu(true);

        //inflate view in which view will be rendered by creating view object in memory
        //when new BoardPixelGridView is created it automatically calls onDraw()
        View rootView = inflater.inflate(R.layout.fragment_minesweeper, container, false);
        board = rootView.findViewById(R.id.boardPixelGridView);

        //receives information from StartScreenFragment about difficulty value
        //checks the KEY
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

    //adding options to options menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add("Home");
    }

    //when item is selected in the options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //add switch case for multiple options in the options menu in the top right corner
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.container_main, new StartScreenFragment()).commit();

        return true;
    }

    //Todo: make a checkGameDone() method
        //checks if the game is done and prompts the endScreenDialog class in the MainActivity
        //returns a boolean true or false depending on if game has ended or not
}
