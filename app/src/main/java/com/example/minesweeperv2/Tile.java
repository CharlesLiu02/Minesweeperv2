package com.example.minesweeperv2;

public class Tile {

    private int row, col;
    private boolean hasBomb;
    private boolean hasFlag = false;
    private boolean isRevealed = false;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //constructor for the tile
        //include hasBomb and hasFlag or not???
    public Tile(int row, int col) {
        this.col = col;
        this.row = row;
    }

    //getters and setters for the instance variables

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean ifHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean ifHasFlag() { return hasFlag; }

    public void setHasFlag(boolean hasFlag) { this.hasFlag = hasFlag; }

    public boolean isRevealed() { return isRevealed; }

    public void setRevealed(boolean revealed) { isRevealed = revealed;  }




    //Todo: getDisplay

}
