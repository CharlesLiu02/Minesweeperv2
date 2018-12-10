package com.example.minesweeperv2;

public class Tile {

    private int xLocation, yLocation;
    private boolean hasBomb;
    private boolean hasFlag = false;
    private boolean isRevealed = false;

        //constructor for the tile
        //include hasBomb and hasFlag or not???
    public Tile(int x, int y) {
        this.xLocation = x;
        this.yLocation = y;
    }

    //getters and setters for the instance variables
    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public boolean ifHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean isHasFlag() { return hasFlag; }

    public void setHasFlag(boolean hasFlag) { this.hasFlag = hasFlag; }

    public boolean isRevealed() { return isRevealed; }

    public void setRevealed(boolean revealed) { isRevealed = revealed;  }


    //Todo: getDisplay

}
