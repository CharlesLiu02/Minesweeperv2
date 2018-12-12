//handles the logic of the game

package com.example.minesweeperv2;

import java.sql.Array;

public class MinesweeperGame {
    private Tile[][] array;
    private int numBombs;

    public MinesweeperGame(int canvasSize, int numBombs) {
        array = new Tile[canvasSize][canvasSize];
        this.numBombs = numBombs;
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


        // if (!array[i][j].ifHasBomb()) {
        //int x = (int) (Math.random() * canvasSize);
        // if (x == 1) {
        // array[i][j].setHasBomb(true);
        //numBombs--;

    }

    public Tile[][] getArray() {
        return array;
    }


    //Todo: make a checkGameDone() method
    //checks if the game is done and prompts the endScreenDialog class in the MainActivity
    //returns a boolean true or false depending on if game has ended or not


    /* public boolean checkGameDone() {

    }*/


    //TODO: create the flagTile method where user holds down and then the flag is drawn on tile
    //if pressed time > 2 seconds && tile has no flag    then draw flag
    //if pressed time > 2 seconds && tile has flag       then figure out way to remove the flag pic from the canvas

    //if pressed time < 2 seconds                        then don't do anything

    //TODO: create the logic for each tile in the onClick type method for a canvas
    //check if tile has or doesn't have bomb then:
    // logic for that
    //if bomb:
    //just draw the bomb pic on top and then prompt TheEndScreenFragment.java
    //if not bomb, check surrounding tiles
    //if surrounding tiles have no bombs then:
    //draw a brown rectangle on top of the green one
    //if not bomb, check surrounding tiles
    //if surrounding tiles have bombs then:
    //use calculateNumber(Tile tile) method (returns an int of #of bombs)
    //just get num pic from drawables and draw the num pic on the tile


    //Number(Tile tile) method to calculate the number image that should be displayed
    //use the xLocation and yLocation of the tile variable passed in and get the tiles surrounding and call ifHasBomb to see if the surrounding tile has a bomb
    //if there is a bomb in a surrounding tile, add a count variable
    //return count

    public int calculateNumber(Tile tile) {
        int count = 0;

        int xLocation = tile.getxLocation();
        int yLocation = tile.getyLocation();

        for (int i = xLocation - 1; i < xLocation + 2; i++) {
            if (array[i][yLocation - 1].ifHasBomb()) {
                count++;
            }
        }

        for (int i = xLocation - 1; i < xLocation + 2; i++) {
            if (array[i][yLocation + 1].ifHasBomb()) {
                count++;
            }
        }

        if (array[xLocation - 1][yLocation].ifHasBomb()) {
            count++;
        }

        if (array[xLocation + 1][yLocation].ifHasBomb()) {
            count++;
        }

        return count;
    }
}



