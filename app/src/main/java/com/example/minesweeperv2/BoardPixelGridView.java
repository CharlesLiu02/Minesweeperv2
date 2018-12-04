package com.example.minesweeperv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BoardPixelGridView extends View {
    private Paint paint = new Paint();
    private int numColumns, numRows;

    public BoardPixelGridView(Context context) {
        super(context);
    }

    public BoardPixelGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardPixelGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSize(int numColumns, int numRows){
        this.numColumns = numColumns;
        this.numRows = numRows;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.rgb(118,255,3));
        paint.setARGB(256, 100, 100, 100);

        //TODO: replace with your own variables
        int numColumns = 5, numRows = 5;
        int cellWidth = getWidth() / numColumns, cellHeight = getHeight() / numRows;

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        paint.setARGB(256, 255, 0, 0);
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                    else{
                        paint.setARGB(256, 100, 100, 100);
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                }else{
                    if (j % 2 == 0) {
                        paint.setARGB(256, 100, 100, 100);
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                    else{
                        paint.setARGB(256, 255, 0, 0);
                        canvas.drawRect(i * cellWidth, j * cellHeight,
                                (i + 1) * cellWidth, (j + 1) * cellHeight, paint);
                    }
                }
            }
        }

//
    }
}
