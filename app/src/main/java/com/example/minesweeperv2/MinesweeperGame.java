//handles the logic of the game

package com.example.minesweeperv2;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;

public class MinesweeperGame {
    private Tile[][] array;
    private int virtualBombs;
    private int canvasSize;
    private boolean isWon;
    private boolean isLost;
    private int bombs;

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    public boolean isWon() {
        return isWon;
    }

    public MinesweeperGame(int canvasSize, int numBombs) {
        isWon = false;
        array = new Tile[canvasSize][canvasSize];
        virtualBombs = numBombs;
        bombs = numBombs;
        this.canvasSize = canvasSize;
        for (int i = 0; i < canvasSize; i++) {
            for (int j = 0; j < canvasSize; j++) {
                array[i][j] = new Tile(i, j);
            }
        }
    }

    public int getBombs() {
        return bombs;
    }

    public Tile[][] getArray() {
        return array;
    }

    //randomize the bombs
    public void randomizeBombsAndSetNumbers() {
        while (virtualBombs > 0) {
            int x = (int) (Math.random() * canvasSize);
            int y = (int) (Math.random() * canvasSize);

            if (!array[y][x].ifHasBomb()) {
                array[y][x].setHasBomb(true);
                virtualBombs--;
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {

                if (array[i][j].ifHasBomb() == false) ;
                {
                    array[i][j].setNumber(calculateNumber(i, j));
                }
            }
        }
    }

    //checks if the game is lost and prompts the victory screen if won

    public void isGameWon() {
        outerLoop:
        for (int i = 0; i < canvasSize; i++) {
            for (int j = 0; j < canvasSize; j++) {

                if (array[i][j].ifHasBomb() == true) {
                    if (array[i][j].ifHasFlag() == true) {

                        isWon = true;
                        break outerLoop;
                    }
                }
            }
        }
        isWon = false;
    }

//    private void revealEverything() {
//        for (int row = 0; row < array.length; row++) {
//            for (int col = 0; col < array[0].length; col++) {
//                array[row][col].setRevealed(true);
//            }
//        }
//    }

    //check if tile has or doesn't have bomb then:
    // logic for that
    //if bomb:
    //just draw the bomb pic on top and then prompt TheEndScreenFragment.java
    //if not bomb, check surrounding tiles
    //if surrounding tiles have no bombs then:
    //draw a brown rectangle on top of the green one
    //if not bomb, check surrounding tiles
    //if surrounding tiles have bombs then:


    //reveals the tile and tiles around it if needed
    public void revealTileAndTilesAround(int row, int col) {

        array[row][col].setRevealed(true);

        if (array[row][col].getNumber() == 0) {
            //checks left side of tile
            for (int i = col + 1; i > col; i--) {
                if (!(i > canvasSize - 1)) {
                    if (array[row][col].ifHasBomb()) {
                        revealAllBombs();
                        setLost(true);
                        break;
                    } else if ((array[row][i].getNumber()) == 0 && !array[row][i].isRevealed() && !array[row][i].ifHasBomb()) {
                        array[row][i].setRevealed(true);
                        revealTileAndTilesAround(row, i);

                    } else if ((array[row][i].getNumber()) > 0 && !array[row][i].isRevealed() && !array[row][i].ifHasBomb()) {
                        array[row][i].setRevealed(true);
                    }
                }
            }

            //checks top of tile
            for (int i = row + 1; i > row; i--) {
                if (!(i > canvasSize - 1)) {
                    if (array[row][col].ifHasBomb()) {
                        revealAllBombs();
                        setLost(true);
                        break;
                    } else if ((array[i][col].getNumber()) == 0 && !array[i][col].isRevealed() && !array[i][col].ifHasBomb()) {
                        array[i][col].setRevealed(true);
                        revealTileAndTilesAround(i, col);

                    } else if ((array[i][col].getNumber()) > 0 && !array[i][col].isRevealed() && !array[i][col].ifHasBomb()) {
                        array[i][col].setRevealed(true);
                    }
                }
            }
            //checks left side of tile
            for (int i = col - 1; i < col; i++) {
                if (!(i < 0)) {
                    if (array[row][col].ifHasBomb()) {
                        revealAllBombs();
                        setLost(true);
                        break;
                    } else if ((array[row][i].getNumber()) == 0 && !array[row][i].isRevealed() && !array[row][i].ifHasBomb()) {
                        array[row][i].setRevealed(true);
                        revealTileAndTilesAround(row, i);

                    } else if ((array[row][i].getNumber()) > 0 && !array[row][i].isRevealed() && !array[row][i].ifHasBomb()) {
                        array[row][i].setRevealed(true);
                    }
                }
            }
            //checks bottom of tile
            for (int i = row - 1; i < row; i++) {
                if (!(i < 0)) {
                    if (array[row][col].ifHasBomb()) {
                        revealAllBombs();
                        setLost(true);
                        break;
                    } else if ((array[i][col].getNumber()) == 0 && !array[i][col].isRevealed() && !array[i][col].ifHasBomb()) {
                        array[i][col].setRevealed(true);
                        revealTileAndTilesAround(i, col);

                    } else if ((array[i][col].getNumber()) > 0 && !array[i][col].isRevealed() && !array[i][col].ifHasBomb()) {
                        array[i][col].setRevealed(true);
                    }
                }
            }
            //if user clicks bomb
            //reveal all bombs
        } else if (array[row][col].ifHasBomb()) {
            revealAllBombs();
            setLost(true);
        } else {
            // if tile is number
            array[row][col].setRevealed(true);
        }
        isGameWon();
    }


    private void revealAllBombs() {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                if (array[row][col].ifHasBomb()) {
                    array[row][col].setRevealed(true);
                }
            }
        }
    }

    // revealing a square on a single click

    public void onSingleTapClickReveal(int row, int col) {


        if (array[row][col].ifHasFlag() == false) {
            revealTileAndTilesAround(row, col);
        }

    }


    //Number(Tile tile) method to calculate the number image that should be displayed
    public int calculateNumber(int row, int col) {
        int count = 0;


        for (int j = row - 1; j <= row + 1; j++) {
            for (int i = col - 1; i <= col + 1; i++) {
                if (!((i < 0) || (i > canvasSize - 1) || (j < 0) || (j > (canvasSize - 1))) &&
                        array[j][i].ifHasBomb() == true) {
                    count++;
                }
            }
        }
        return count;
    }
}




