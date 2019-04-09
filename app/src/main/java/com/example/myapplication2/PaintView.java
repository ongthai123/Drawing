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

    ArrayList <Point> point1 = new ArrayList<Point>();
    ArrayList <Point> point2 = new ArrayList<>();
    ArrayList <Point> p1 = new ArrayList<>();
    ArrayList <Point> p2 = new ArrayList<>();

    public int shapeSize = 20;
    public int undo1 = 0;
    public  int undo2 = 0;
//    public Random random = new Random();
    public int color1 = 0;
    public int color2 = 0;

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

    public void Undo(){
        //Point 1
        //for replace new list when there is still undo value
        if(p1.size() > 0 && point1.size() > p1.size()){
            p1.clear();
            p1.addAll(point1);
            point1.clear();
            undo1 += 1;

            for(int i = 0; i < p1.size() - undo1; i++){
                point1.add(p1.get(i));
            }
        }
        //after first click
        else if(p1.size() > 0){
            if(undo1 < p1.size()) undo1 += 1;
            point1.clear();

            for(int i = 0; i < p1.size() - undo1; i++){
                point1.add(p1.get(i));
            }
        }
        //for first click
        else if(p1.size() == 0){
            undo1 += 1;
            p1.addAll(point1);
            point1.clear();

            for(int i = 0; i < p1.size() - undo1; i++){
                point1.add(p1.get(i));
            }
        }

        //Point 2
        //for replace new list when there is still undo value
        if(p2.size() > 0 && point2.size() > p2.size()){
            p2.clear();
            p2.addAll(point2);
            point2.clear();
            undo2 += 1;

            for(int i = 0; i < p2.size() - undo2; i++){
                point2.add(p2.get(i));
            }
        }
        //after first click
        else if(p2.size() > 0){
            if(undo2 < p2.size()) undo2 += 1;
            point2.clear();

            for(int i = 0; i < p2.size() - undo2; i++){
                point2.add(p2.get(i));
            }
        }
        //for first click
        else if(p2.size() == 0){
            undo2 += 1;
            p2.addAll(point2);
            point2.clear();

            for(int i = 0; i < p2.size() - undo2; i++){
                point2.add(p2.get(i));
            }
        }
        invalidate();
    }

    public void Redo(){
        if(p1.size() > point1.size()){
            point1.add(p1.get(p1.size() - undo1));
            undo1 -= 1;
        }

        if(p2.size() > point2.size()){
            point2.add(p2.get(p2.size() - undo2));
            undo2 -= 1;
        }

        invalidate();
    }
    final Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < point1.size(); i++){
            paint.setColor(point1.get(i).colour);
            canvas.drawCircle(point1.get(i).x, point1.get(i).y, point1.get(i).size, paint);
            canvas.drawCircle(50, 50, point1.get(i).size, paint);
        }

        for(int j = 0; j < point2.size(); j++){
            paint.setColor((point2.get(j)).colour);
            canvas.drawCircle(point2.get(j).x, point2.get(j).y, point2.get(j).size, paint);
            canvas.drawCircle(200, 50, point1.get(j).size, paint);
        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Random random = new Random();
        boolean onHold = false;

        color1 = random.nextInt();
        color2 = random.nextInt();
        int pointerCount = event.getPointerCount();

        if(undo1 > 0){
            undo1 = 0;
            p1.clear();
        }
        if(undo2 > 0){
            undo2 = 0;
            p2.clear();
        }



        for(int i = 0; i < pointerCount; i++){
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);
            int id = (int) event.getPointerId(i);

            if(id == 0){
                Point p1 = new Point(x,y,shapeSize,color1);
                point1.add(p1);
            }
            if(id == 1){
                Point p2 = new Point(x,y,shapeSize,color2);
                point2.add((p2));
            }
        }
        invalidate();
        return true;
    }

}
