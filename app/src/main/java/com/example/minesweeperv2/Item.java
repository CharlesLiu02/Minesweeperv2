package com.example.minesweeperv2;

public class Item {
    private int drawableId;
    private int left, right, top, bottom;

    public Item(int drawableId, int left, int top, int right, int bottom) {
        this.drawableId = drawableId;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    // have an ArrayList<Item> that you add to in the onTouch & invalidate()
    // onDraw will loop through the list drawing every item in the list
        // drawable.draw(left, right, top, bottom)
}
