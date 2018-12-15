//handles the logic of the game

package com.example.minesweeperv2;

import android.graphics.Canvas;

import java.sql.Array;

public class MinesweeperGame {
    private Tile[][] array;
    private int numBombs;
    private int canvasSize;

    public MinesweeperGame(int canvasSize, int numBombs) {
        array = new Tile[canvasSize][canvasSize];
        this.numBombs = numBombs;
        this.canvasSize = canvasSize;
        for (int i = 0; i < canvasSize; i++) {
            for (int j = 0; j < canvasSize; j++) {
                array[i][j] = new Tile(i, j);
            }
        }

        // randomize the bombs
        //or if numBombs == 0, when there are no bombs left to place, then the constructor ends

        while (numBombs > 0) {
            int x = (int) (Math.random() * canvasSize);
            int y = (int) (Math.random() * canvasSize);
            {
                if (!array[x][y].ifHasBomb()) {
                    array[x][y].setHasBomb(true);
                    numBombs--;
                }
            }
        }

        // probability method
        // if (!array[i][j].ifHasBomb()) {
        //int x = (int) (Math.random() * canvasSize);
        // if (x == 1) {
        // array[i][j].setHasBomb(true);
        //numBombs--;

    }

    public Tile[][] getArray() {
        return array;
    }


    //checks if the game is lost and prompts the

    public boolean gameWon() {

        boolean x = true;

        for (int i = 0; i < canvasSize; i++) {
            for (int j = 0; j < canvasSize; j++) {

                if (array[i][j].ifHasBomb() == true) {
                    if (array[i][j].ifHasFlag() == true) {
                        x = true;
                    } else {
                        x = false;
                    }
                }

            }
        }

            if (x == true){
                return true;
                // prompt victory fragment screen
            } else {
                return false;
                //continue game
            }
        }




        public void gameLost()
        {
            //prompt losing screen fragment

        }


        //if flag is drawn on canvas then set same ifHasFlag to true on the tile with corresponding location
        //if flag is undrawn on canvas then set same ifHasFlag to false on the tile with corresponding location

        public void ifFlagDrawn ( int row, int col)
        {
            array[row][col].setHasFlag(true);
        }

        public void ifFlagUndrawn ( int row, int col)
        {
            array[row][col].setHasFlag(false);
        }


        //check if tile has or doesn't have bomb then:
        // logic for that
        //if bomb:
        //just draw the bomb pic on top and then prompt TheEndScreenFragment.java
        //if not bomb, check surrounding tiles
        //if surrounding tiles have no bombs then:
        //draw a brown rectangle on top of the green one
        //if not bomb, check surrounding tiles
        //if surrounding tiles have bombs then:


        public void revealTileAndTilesAround(int row, int col) {

            array[row][col].setNumber(calculateNumber(array[row][col]));
            array[row][col].setRevealed(true);

            for (int i = col - 1; i <= col + 1; i++) {
                for (int j = row - 1; j <= row + 1; j++){

                    if (calculateNumber(array[j][i]) == 0 && array[j][i].isRevealed() == false) {

                        array[j][i].setNumber(0);
                        array[j][i].setRevealed(true);
                        revealTileAndTilesAround(j , i);

                    }
                    else{
                        array[j][i].setNumber(calculateNumber(array[j][i]));
                        array[j][i].setRevealed(true);
                    }
                    }
                }
            }


        public void onSingleTapClickReveal (int row, int col) {

            if (array[row][col].ifHasBomb() == true) {

                gameLost();

            }
            else if (array[row][col].ifHasBomb() == false) {


                revealTileAndTilesAround(row , col);{


                }
            }
        }




        //Number(Tile tile) method to calculate the number image that should be displayed
        //use the xLocation and yLocation of the tile variable passed in and get the tiles surrounding and call ifHasBomb to see if the surrounding tile has a bomb
        //if there is a bomb in a surrounding tile, add a count variable
        //return count
        //use calculateNumber(Tile tile) method (returns an int of #of bombs)
        //just get num pic from drawables and draw the num pic on the tile

        public int calculateNumber (Tile tile){
            int count = 0;

            int row  = tile.getRow();
            int col = tile.getCol();

            for (int i = col - 1; i < col + 2; i++) {
                if (array[row - 1][i].ifHasBomb()) {
                    count++;
                }
            }

            for (int i = col - 1; i < col + 2; i++) {
                if (array[row + 1][i].ifHasBomb()) {
                    count++;
                }
            }

            if (array[row][col - 1].ifHasBomb()) {
                count++;
            }

            if (array[row][col + 1].ifHasBomb()) {
                count++;
            }

            return count;
        }
    }




