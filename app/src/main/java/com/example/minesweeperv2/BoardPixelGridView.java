package com.example.minesweeperv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BoardPixelGridView extends View {
    private Paint paint;
    private int numColumns, numRows;
    private Canvas canvas;

    public BoardPixelGridView(Context context) {
        super(context);

        init(null);
    }

    public BoardPixelGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public BoardPixelGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    //accounts for size, orientation changes to view
    private void init(@Nullable AttributeSet set){
        //declare instance variables here instead of onDraw() so that they are not continuously created
        //make paint drawing smoother
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    public void setSize(int numColumns, int numRows){
        this.numColumns = numColumns;
        this.numRows = numRows;
    }


    //TODO: figure out a way to randomize bomb placement on the canvas
        //idk how to do


    //call postInvalidate() after updating canvas to redraw the view
    @Override
    protected void onDraw(Canvas canvas) {

        //TODO: set the canvas passed as the canvas instance variable
        //canvas = this.canvas;

        int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        paint.setColor(Color.rgb(118,255,3));

                        //TODO: create tile obj and call draw method in the tile class on the object
                        //TODO: do canvas.tile.draw( variables )
                        /*Tile tile = new Tile(i,j);*/

                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                    else{
                        paint.setColor(Color.rgb(118,212,3));
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                }else{
                    if (j % 2 == 0) {
                        paint.setColor(Color.rgb(118,212,3));
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                    else{
                        paint.setColor(Color.rgb(118,255,3));
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                }
            }
        }

//
    }
}
