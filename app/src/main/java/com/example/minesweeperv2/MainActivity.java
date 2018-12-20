//where everything runs

package com.example.minesweeperv2;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //replaces container main with start screen fragment
        //when app starts
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container_main, new StartScreenFragment()).commit();

        //todo: get info about game done or not by using the checkGameDone() method in the MinsweeperFragment class
            //if method returns true then the endScreenDialog is shown
            //else do nothing

        //when game done, then create the dialog
//        if (MinesweeperFragment.getGame().gameWon()) {
//            return;
//        }
        showResultsDialog();
    }

    private void showResultsDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //need for creating custom view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_end_screen, null);

        //initalize variables
        TextView textView = view.findViewById(R.id.textView_frag_end_screen_game_over);

        alertDialogBuilder.setView(view);

        alertDialogBuilder.create().show();
    }
}
