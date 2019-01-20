//the home screen of the game with 3 options easy medium and hard

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
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class StartScreenFragment extends Fragment{

    private Button buttonEasy, buttonMedium, buttonHard;
    private ImageView imageViewTitle, imageViewPicture;

    public static final String KEY = "difficulty key";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate view in which view will be rendered by creating view object in memory
        View rootView = inflater.inflate(R.layout.fragment_start_screen, container, false);

        //wired the widgets
        buttonEasy = rootView.findViewById(R.id.button_start_screen_easy);
        buttonMedium = rootView.findViewById(R.id.button_start_screen_medium);
        buttonHard = rootView.findViewById(R.id.button_start_screen_hard);
        imageViewTitle = rootView.findViewById(R.id.imageView_start_title);
        imageViewPicture = rootView.findViewById(R.id.imageView_start_picture);

        Glide.with(this).load(R.drawable.minesweeper).into(imageViewTitle);
        Glide.with(this).load(R.drawable.mountains).into(imageViewPicture);


        //replaces start screen fragment with minesweeper fragment when the button is clicked
        //setting the onClickListeners to create a new listener when clicked
        buttonEasy.setOnClickListener(new Listener());
        buttonMedium.setOnClickListener(new Listener());
        buttonHard.setOnClickListener(new Listener());
        return rootView;
    }

    //listener class that the onClickListener of the buttons uses
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
                case R.id.button_start_screen_easy:
                    editor.putString(KEY, "Easy");
                    editor.commit();
                    fm.beginTransaction().replace(R.id.container_main, new MinesweeperFragment()).commit();
                    break;
                case R.id.button_start_screen_medium:
                    editor.putString(KEY, "Medium");
                    editor.commit();
                    fm.beginTransaction().replace(R.id.container_main, new MinesweeperFragment()).commit();
                    break;
                case R.id.button_start_screen_hard:
                    editor.putString(KEY, "Hard");
                    editor.commit();
                    fm.beginTransaction().replace(R.id.container_main, new MinesweeperFragment()).commit();
                    break;
            }

        }
    }

}
