package com.example.minesweeperv2;

import android.view.View;

public class Game {

    private int canvasLength;
    private int canvasWidth;


   public Tile[][] array;

    public Tile[][] getArray() { return array;
    }

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



    //TODO: create a calculateNumber(Tile tile) method to calculate the number image that should be displayed
    //use the xLocation and yLocation of the tile variable passed in and get the tiles surrounding and call ifHasBomb to see if the surrounding tile has a bomb
    //if there is a bomb in a surrounding tile, add a count variable
    //return count

    public int calculateNumber(Tile tile){
        int count = 0;

        tile.getxLocation();
        tile.getyLocation();


        //do a for loop the do if else inside
        //if else logic here
        //get the tile next to this one
        //how to do idk????
        //count++ if bomb in the surrounding tile
        //do nothing if no bomb in the surrounding tile


        return count;
    }

    //TODO: implement an onDraw method for each tile
    //take the code fom the BoardPixelGridView.java for drawing the rectangles and put it here
}
