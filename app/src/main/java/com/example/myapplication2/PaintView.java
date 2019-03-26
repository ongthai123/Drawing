package com.example.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class PaintView extends View implements View.OnTouchListener {

    ArrayList <Point> points = new ArrayList<Point>();
    ArrayList <Point> temp = new ArrayList<>();
//    ArrayList <Point> redo = new ArrayList<>();

    public int shapeSize = 20;
//    public int undo = 0;
//    public  int redo = 0;
//    public int currentIndex = points.size() - undo + redo;
    class Point {
        float x;
        float y;
        int size;
        int colour;
        public Point(float x, float y, int size, int colour){
            this.x = x;
            this.y = y;
            this.size = size;
            this.colour = colour;
        }
    }

    public PaintView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    final Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < points.size(); i++){
            paint.setColor(points.get(i).colour);
            canvas.drawCircle(points.get(i).x, points.get(i).y, points.get(i).size, paint);
        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Random random = new Random();

        Point point = new Point(event.getX(),event.getY(), shapeSize, random.nextInt());
//        points.add(point);

        if(temp.size() != 0) {
            points.set(points.size() - temp.size(),point);
            temp.clear();
        }
        else{
            points.add(point);
        }

        invalidate();
        return true;
    }

}
