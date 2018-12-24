package com.example.minesweeperv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EndScreenFragment extends Fragment {

    private Button homeButton;
    private TextView gameOver;
    public static final String KEY = "end";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate view in which view will be rendered by creating view object in memory
        View rootView = inflater.inflate(R.layout.fragment_lost_screen, container, false);

        //wired the widgets
        homeButton = rootView.findViewById(R.id.button_frag_lost_screen_home);

        //replaces start screen fragment with minesweeper fragment when the button is clicked
        //setting the onClickListeners to create a new listener when clicked
        homeButton.setOnClickListener(new Listener());

        return rootView;
    }

    private class Listener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            //opens minesweeper view
            //replaces container main that holds start screen fragment with minesweeper fragment
            //depending on the difficulty
            FragmentManager fm = getFragmentManager();

            //sends information about which button(easy, medium, or hard) the user clicked
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            //switch case to check which button is pressed and sends info
            //creates a new MinesweeperFragment when clicked
            //commit() saves the changes in the shared preferences
            switch(view.getId()) {
                case R.id.button_frag_lost_screen_home:
                    editor.putString(KEY, "end");
                    editor.commit();
                    Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
                    fm.beginTransaction().replace(R.id.container_main, new StartScreenFragment()).commit();
                    break;
            }

        }
    }
}

