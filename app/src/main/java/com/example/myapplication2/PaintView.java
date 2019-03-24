package com.example.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class PaintView extends View implements View.OnTouchListener {

    ArrayList <Point> points = new ArrayList<Point>();

    class Point {
        float x;
        float y;
        int colour;
        public Point(float x, float y, int colour){
            this.x = x;
            this.y = y;
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

    float x;
    float y;
    final Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.RED);

        for(Point p: points)
        {
            paint.setColor(p.colour);
            canvas.drawCircle(p.x,p.y,20,paint);

        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Random random = new Random();

        Point point = new Point(event.getX(),event.getY(), random.nextInt());

        points.add(point);
        invalidate();
        return true;
    }

}
