//shows the actual minesweeper game with the board

package com.example.minesweeperv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

public class MinesweeperFragment extends Fragment {
    public BoardPixelGridView gameView;
    public MinesweeperGame game;
    private Chronometer chronometer;
    private TextView textViewFlags;
    IfGameOverListener mCallBack;

    public MinesweeperGame getGame() {
        return game;
    }

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
        gameView = rootView.findViewById(R.id.boardPixelGridView);
        textViewFlags = rootView.findViewById(R.id.textView_frag_minesweeper_flags);


        chronometer = rootView.findViewById(R.id.chronometer_frag_minesweeper_timer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        //implements our own listener for the gameView
        gameView.onGridTouchedListener(new BoardPixelGridView.OnGridTouchedListener() {
            @Override
            public void onTouch(int row, int col) {
                game.onSingleTapClickReveal(row, col);
                passIfLost();
                if (game.isLost()) {
                    chronometer.stop();
                    passShowTime();
                }

            }

            @Override
            public void onLongTouch(int row, int col) {
            }

            @Override
            public void updateFlags() {
                //calculates amount of flags user has left
                Tile[][] array = game.getArray();
                int bombs = game.getBombs();
                int flags = 0;
                for (int r = 0; r < array.length; r++) {
                    for (int c = 0; c < array[0].length; c++) {
                        if (array[r][c].ifHasFlag()) {
                            flags++;
                        }
                    }
                }
                textViewFlags.setText(bombs - flags + "");
            }

            @Override
            public void ifWon() {
                game.isGameWon();
                passIfWon();
                if(game.isWon()){
                    chronometer.stop();
                    passShowTime();
                }
            }
        });

        //receives information from StartScreenFragment about difficulty value
        //checks the KEY
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String difficulty = sharedPref.getString(StartScreenFragment.KEY, "Easy");
        //depending on difficulty, sets size of the gameView
        //creates a new game and sets size of the game
        if (difficulty.equals("Easy")) {
            gameView.setSize(10, 10);
            game = new MinesweeperGame(10, 10);
            game.randomizeBombsAndSetNumbers();
            gameView.setBoard(game.getArray());
            textViewFlags.setText("10");
        } else if (difficulty.equals("Medium")) {
            gameView.setSize(12, 12);
            game = new MinesweeperGame(12, 25);
            game.randomizeBombsAndSetNumbers();
            gameView.setBoard(game.getArray());
            textViewFlags.setText("25");
        } else {
            gameView.setSize(15, 15);
            game = new MinesweeperGame(15, 45);
            game.randomizeBombsAndSetNumbers();
            gameView.setBoard(game.getArray());
            textViewFlags.setText("45");
        }
        return rootView;

    }

    private int showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        return (int) elapsedMillis;
    }

    public BoardPixelGridView getGameView() {
        return gameView;
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

//    public void setIfGameWonListener(Activity activity){
//        mCallBack = (IfGameWonListener) activity;
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (IfGameOverListener) context;
    }

    public void passIfLost() {
        mCallBack.ifGameLost(game.isLost());
    }

    public void passIfWon(){
        mCallBack.ifGameWon(game.isWon());
    }

    public void passShowTime() {
        mCallBack.showTime(showElapsedTime());
    }

    public interface IfGameOverListener {
        void ifGameLost(boolean ifLost);

        void ifGameWon(boolean ifWon);

        void showTime(int time);
    }
}
