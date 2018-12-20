package com.example.minesweeperv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
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
    private Item[][] arrayItems;

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

            public void onLongPress(MotionEvent event) {
                int r = getRow(event);
                int c = getCol(event);

                Log.e("row", "" + r);
                Log.e("col", "" + c);

                listener.onLongTouch(r, c);

                Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();

                int cellHeight = getHeight() / numRows;
                int cellWidth = getWidth() / numColumns;

                int left = cellWidth * getCol(event);
                int top = cellHeight * getRow(event);
                int right = cellWidth * (getCol(event) + 1);
                int bottom = cellHeight * (getRow(event) + 1);

                //checks if any flags are on grid
                //if there are, then it checks to see whether or not he position the user clicked has a flag
                //if it does, the flag is removed
                //if it doesn't, a flag is added
                if (items.size() != 0) {
                    for (int i = 0; i < items.size(); i++) {
                        if ((left == items.get(i).getLeft()) && (top == items.get(i).getTop())
                                && (right == items.get(i).getRight()) && (bottom == items.get(i).getBottom())) {
                            items.remove(i);
                            board[r][c].setHasFlag(false);
                            break;
                        } else {
                            if (!isFlag(event)) {
                                //adds a flag item to the arraylist of items that needs to be drawn
                                items.add(new Item(R.drawable.minesweeper_flag, left, top, right, bottom));
                                board[r][c].setHasFlag(true);
                                break;
                            }
                        }
                    }
                } else {
                    items.add(new Item(R.drawable.minesweeper_flag, left, top, right, bottom));
                    board[r][c].setHasFlag(true);
                }
                invalidate();
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int r = getRow(event);
                int c = getCol(event);

                Log.e("row", "" + r);
                Log.e("col", "" + c);

                listener.onTouch(r, c);
                Log.e("onsingletapup", "onsingletapup");

                Toast.makeText(getContext(), "short click", Toast.LENGTH_SHORT).show();

                int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;

                int left = cellWidth;
                int right = cellWidth;
                int top = cellHeight;
                int bottom = cellHeight;

                for (int row = 0; row < board.length; row++) {
                    for (int col = 0; col < board[row].length; col++) {
                        Log.e("row", "" + row);
                        Log.e("col", "" + col);
                        Log.e("reveal", "" + board[row][col].isRevealed());
                        Log.e("number", "" + board[row][col].getNumber());
                        if (board[row][col].isRevealed()) {
                            if (board[row][col].ifHasBomb()) {
                                items.add(new Item(R.drawable.bomb, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 0) {
                                items.add(new Item(R.drawable.minesweeper_0, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 1) {
                                items.add(new Item(R.drawable.minesweeper_1, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 2) {
                                items.add(new Item(R.drawable.minesweeper_2, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 3) {
                                items.add(new Item(R.drawable.minesweeper_3, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 4) {
                                items.add(new Item(R.drawable.minesweeper_4, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 5) {
                                items.add(new Item(R.drawable.minesweeper_5, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 6) {
                                items.add(new Item(R.drawable.minesweeper_6, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else if (board[row][col].getNumber() == 7) {
                                items.add(new Item(R.drawable.minesweeper_7, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            } else {
                                items.add(new Item(R.drawable.minesweeper_8, (col) * cellWidth, (row) * cellHeight, (col + 1) * cellWidth, (row + 1) * cellHeight));
                            }
                        }
                    }
                }
                invalidate();

                return true;
            }
        });
    }

    //checks if tile already has a flag drawn or not
    private boolean isFlag(MotionEvent e) {
        int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;
        int left = 0, top = 0, right = 0, bottom = 0;

        left = cellWidth * getCol(e);
        top = cellHeight * getRow(e);
        right = cellWidth * (getCol(e) + 1);
        bottom = cellHeight * (getRow(e) + 1);

        for (int i = 0; i < items.size(); i++) {
            int itemLeft = items.get(i).getLeft();
            int itemTop = items.get(i).getTop();
            int itemRight = items.get(i).getRight();
            int itemBottom = items.get(i).getBottom();
            if (left == itemLeft && right == itemRight && top == itemTop && bottom == itemBottom) {
                return true;
            }
        }
        return false;
    }

    //call postInvalidate() after updating canvas to redraw the view
    @Override
    protected void onDraw(Canvas canvas) {

        //TODO: set the canvas passed as the canvas instance variable
        //canvas = this.canvas

        int cellHeight = getHeight() / numRows, cellWidth = getWidth() / numColumns;

        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        //draws green where tile is not revealed
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (!board[row][col].isRevealed()) {
                    if (row % 2 == 0) {
                        if (col % 2 == 0) {
                            paint.setColor(Color.rgb(118, 255, 3));
                            canvas.drawRect(col * cellWidth, row * cellHeight,
                                    (col + 1) * cellWidth, (row + 1) * cellHeight, paint);
                        } else {
                            paint.setColor(Color.rgb(118, 212, 3));
                            canvas.drawRect(col * cellWidth, row * cellHeight,
                                    (col + 1) * cellWidth, (row + 1) * cellHeight, paint);
                        }
                    } else {
                        if (col % 2 == 0) {
                            paint.setColor(Color.rgb(118, 212, 3));
                            canvas.drawRect(col * cellWidth, row * cellHeight,
                                    (col + 1) * cellWidth, (row + 1) * cellHeight, paint);
                        } else {
                            paint.setColor(Color.rgb(118, 255, 3));
                            canvas.drawRect(col * cellWidth, row * cellHeight,
                                    (col + 1) * cellWidth, (row + 1) * cellHeight, paint);
                        }
                    }
                }
            }
        }

        //draws flags
        //draws tiles that are revealed
        for (int i = 0; i < items.size(); i++) {
            Drawable item = ContextCompat.getDrawable(getContext(), items.get(i).getDrawableId());
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

        this.gestureDetector.onTouchEvent(event);
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

        void onLongTouch(int row, int col);
    }
}
