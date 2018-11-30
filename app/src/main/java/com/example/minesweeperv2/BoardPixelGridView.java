package com.example.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.minesweeperv2.Tile;

public class BoardPixelGridView extends View {

    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint paint = new Paint();
    private Tile[][] Board;

    private boolean isBomb, isNumber;

    //handling the flag
    private boolean isActionDown = false;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //TODO: Instead of using if() to check if the view is clicked,
            //TODO: cancel the runnable when in action up when the time clicked is less than the flag time...?
            if (isActionDown) {
                //flag it
                Toast.makeText(getContext(), "Flagged", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public BoardPixelGridView(Context context) {
        this(context, null);
    }

    public BoardPixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        Board = new Tile[numColumns][numRows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                canvas.drawRect(i * cellWidth, j * cellHeight,
                        (i + 1) * cellWidth, (j + 1) * cellHeight,
                        paint);
            }
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, paint);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, paint);
        }
    }

    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public void onLongPress(MotionEvent e) {
            Log.e("", "Longpress detected");
        }
    });


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            isActionDown = true;
            handler.postDelayed(runnable, 1000);

            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);

            //Board[column][row] = __________;
            invalidate();
        }
        else if (event.getAction() == MotionEvent.ACTION_UP){
            isActionDown = false;
        }

        return true;
    }
}

