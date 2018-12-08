//where everything runs

package com.example.minesweeperv2;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        showResultsDialog();
    }

    private void showResultsDialog() {
        //displaying end screen fragment
        FragmentManager fm = getSupportFragmentManager();
        //sets title of end screen fragment
        EndScreenDialog endScreenDialog = EndScreenDialog.newInstance("End Game");
        //calls the style to show title
        endScreenDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        endScreenDialog.show(fm, "fragment_end_screen");

    }
}
