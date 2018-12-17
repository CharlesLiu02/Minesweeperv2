package com.example.minesweeperv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class BoardPixelGridView extends View {
    private Paint paint;
    private int numColumns, numRows;
    private Canvas canvas;
    private OnGridTouchedListener listener = null;
    private GestureDetector gestureDetector = null;
    private ArrayList<Item> items;
    private Tile[][] board;

    public BoardPixelGridView(Context context) {
        super(context);

        init(null);
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public BoardPixelGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public BoardPixelGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }


    public void setSize(int numColumns, int numRows) {
        this.numColumns = numColumns;
        this.numRows = numRows;
    }

    //accounts for size, orientation changes to view
    private void init(@Nullable AttributeSet set) {
        //declare instance variables here instead of onDraw() so that they are not continuously created
        //make paint drawing smoother
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        items = new ArrayList<>();

        //gesture detector is used to handle long and short clicks
        setClickable(true);
        setFocusable(true);
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            public void onLongPress(MotionEvent e) {
                Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();

                int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;
                int left = 0, top = 0, right = 0, bottom = 0;

                left = cellWidth * getCol(e);
                top = cellHeight * getRow(e);
                right = cellWidth * (getCol(e) + 1);
                bottom = cellHeight * (getRow(e) + 1);

                //checks if any flags are on grid
                //if there are, then it checks to see whether or not he position the user clicked has a flag
                //if it does, the flag is removed
                //if it doesn't, a flag is added
                if (items.size() != 0) {
                    for (int i = 0; i < items.size(); i++) {
                        if ((left == items.get(i).getLeft()) && (top == items.get(i).getTop())
                                && (right == items.get(i).getRight()) && (bottom == items.get(i).getBottom())) {
                            items.remove(i);
                            break;
                        } else {
                            if(!isFlag(e)){
                                //adds a flag item to the arraylist of items that needs to be drawn
                                items.add(new Item(R.drawable.minesweeper_flag, left, top, right, bottom));
                                break;
                            }
                        }
                    }
                } else {
                    items.add(new Item(R.drawable.minesweeper_flag, left, top, right, bottom));
                }
                invalidate();

            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Toast.makeText(getContext(), "short click", Toast.LENGTH_SHORT).show();

                int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;
                int left = 0, top = 0, right = 0, bottom = 0;
/*
                left = cellWidth * getCol(e);
                top = cellHeight * getRow(e);
                right = cellWidth * (getCol(e) + 1);
                bottom = cellHeight * (getRow(e) + 1);*/

                MinesweeperFragment.game.onSingleTapClickReveal(getRow(e), getCol(e));
                /*Tile[][] array = MinesweeperFragment.game.getArray();
                for(int i = 0; i < array.length; i++){
                    for(int j = 0; j < array.length;j++) {
                        if (array[i][j].isRevealed()) {
                            if (!isDrawn(e)) {
                                 if (array[i][j].ifHasBomb()) {
                                    items.add(new Item(R.drawable.bomb, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                }
                                else if (array[i][j].getNumber() == 0) {
                                    items.add(new Item(R.drawable.minesweeper_0, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 1) {
                                    items.add(new Item(R.drawable.minesweeper_1, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 2) {
                                    items.add(new Item(R.drawable.minesweeper_2, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 3) {
                                    items.add(new Item(R.drawable.minesweeper_3, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 4) {
                                    items.add(new Item(R.drawable.minesweeper_4, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 5) {
                                    items.add(new Item(R.drawable.minesweeper_5, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 6) {
                                    items.add(new Item(R.drawable.minesweeper_6, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 7) {
                                    items.add(new Item(R.drawable.minesweeper_7, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                } else if (array[i][j].getNumber() == 8) {
                                    items.add(new Item(R.drawable.minesweeper_8, left + (j * cellWidth), top + (i * cellHeight), right + (j * cellWidth), bottom + (i * cellWidth)));
                                }
                            }
                        }
                    }
                }*/
                invalidate();

                return true;
            }
        });
    }

    private boolean isDrawn(MotionEvent e) {
        int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;
        int left = 0, top = 0, right = 0, bottom = 0;

        left = cellWidth * getCol(e);
        top = cellHeight * getRow(e);
        right = cellWidth * (getCol(e) + 1);
        bottom = cellHeight * (getRow(e) + 1);

        for(int i = 0; i < items.size(); i++){
            int itemLeft = items.get(i).getLeft();
            int itemTop = items.get(i).getTop();
            int itemRight = items.get(i).getRight();
            int itemBottom = items.get(i).getBottom();
            if(left == itemLeft && right == itemRight && top == itemTop && bottom == itemBottom){
                return true;
            }
        }
        return false;
    }

    //checks if tile already has a flag drawn or not
    private boolean isFlag(MotionEvent e) {
        int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;
        int left = 0, top = 0, right = 0, bottom = 0;

        left = cellWidth * getCol(e);
        top = cellHeight * getRow(e);
        right = cellWidth * (getCol(e) + 1);
        bottom = cellHeight * (getRow(e) + 1);

        for(int i = 0; i < items.size(); i++){
            int itemLeft = items.get(i).getLeft();
            int itemTop = items.get(i).getTop();
            int itemRight = items.get(i).getRight();
            int itemBottom = items.get(i).getBottom();
            if(left == itemLeft && right == itemRight && top == itemTop && bottom == itemBottom){
                return true;
            }
        }
        return false;
    }


    //TODO: figure out a way to draw the randomize bomb placement on the canvas
    //idk how to do


    //call postInvalidate() after updating canvas to redraw the view
    @Override
    protected void onDraw(Canvas canvas) {

        //TODO: set the canvas passed as the canvas instance variable
        //canvas = this.canvas

        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (!board[row][col].isRevealed()) {
                    //TODO: draw a green
                } else if ()
            }
        }

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        paint.setColor(Color.rgb(118, 255, 3));

                        //TODO: create tile obj and call draw method in the tile class on the object
                        //TODO: do canvas.tile.draw( variables )
                        /*Tile tile = new Tile(i,j);*/

                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    } else {
                        paint.setColor(Color.rgb(118, 212, 3));
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                } else {
                    if (j % 2 == 0) {
                        paint.setColor(Color.rgb(118, 212, 3));
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    } else {
                        paint.setColor(Color.rgb(118, 255, 3));
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                }
            }
        }

        //checks which items it has to draw
        for (int i = 0; i < items.size(); i++) {
            Drawable item = ResourcesCompat.getDrawable(getResources(), items.get(i).getDrawableId(), null);
            int left = items.get(i).getLeft();
            int right = items.get(i).getRight();
            int top = items.get(i).getTop();
            int bottom = items.get(i).getBottom();
            item.setBounds(left, top, right, bottom);
            item.draw(canvas);
        }
    }

    //get the row and column that was clicked
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int row = getRow(event);
        int col = getCol(event);

        Log.e("row", "" + row);
        Log.e("col", "" + col);
        listener.onTouch(row, col);

        return gestureDetector.onTouchEvent(event);
    }

    private int getCol(MotionEvent event) {
        int col = -1; //column that the user clicked
        int userX = (int) event.getX();
        int cellWidth = getWidth() / numColumns;
        int setCol = 0;
        for (int c = 1; c <= numColumns; c++) {
            //checks if user clicked is within bounds of cell
            //if it is, it sets to the corresponding row
            //if it isn't it keeps on looping
            if (userX < (c * cellWidth)) {
                col = setCol;

                //checks if column is set yet
                if (col != -1) {
                    break;
                }
            }
            setCol++;
        }
        return col;
    }

    private int getRow(MotionEvent event) {
        int row = -1; //row that the user clicked
        int userY = (int) event.getY();
        int cellHeight = getHeight() / numRows;
        int setRow = 0;
        //checks which row user clicked
        for (int r = 1; r <= numRows; r++) {
            //checks if user clicked is within bounds of cell
            //if it is, it sets to the corresponding row
            //if it isn't it keeps on looping
            if (userY < (r * cellHeight)) {
                row = setRow;

                //checks if row is set yet
                if (row != -1) {
                    break;
                }
            }
            setRow++;
        }
        return row;
    }


    //sets listener
    public void onGridTouchedListener(OnGridTouchedListener listener) {
        this.listener = listener;
    }

    //create own interface to handle clicks for grid
    public interface OnGridTouchedListener {
        void onTouch(int row, int col);
    }
}
