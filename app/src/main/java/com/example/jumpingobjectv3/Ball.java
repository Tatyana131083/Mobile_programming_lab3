package com.example.jumpingobjectv3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private final int RADIUS = 20;
    private final int REVERSE = -1;
    private int x;
    private int y;
    private float velX;
    private float velY;
    private int leftWall;
    private int topWall;
    private int rightWall;
    private int bottomWall;
    private  int h;
    private boolean aLife;
    private String character;

    public Ball(int x, int y, int screenX, int screenY, String character) {
        this.x = x;
        this.y = y;
        velX = 1;
        velY = 12;
        rightWall = screenX;
        leftWall = 0;
        bottomWall = screenY;
        topWall =0;
        h = screenY - y;
        aLife = true;
        this.character = character;
    }
    public boolean getALife(){
        return  aLife;
    }

    public void move() {
        //move ball
        x += velX;
        y += velY;

        //check for collisions
        //top and button collision
        if (y > (bottomWall - RADIUS)) {
            y = bottomWall - RADIUS;
            velY = velY * REVERSE * 0.75f;
            h = (5*h)/6;
            topWall = bottomWall - h;
            if (h <= 2*RADIUS){
                aLife = false;
            }
        } else if (y < topWall + RADIUS) {
            y = topWall + RADIUS;
            velY *= REVERSE;
            velY += 4;
        }
        //left and right collisions
        if (x > rightWall - RADIUS) {
            x = rightWall - RADIUS;
            velX *= REVERSE;
        } else if (x < leftWall + RADIUS) {
            x = leftWall + RADIUS;
            velX *= REVERSE;
        }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(171, 205, 239));
        canvas.drawCircle(x, y, RADIUS, paint);
        paint.setColor(Color.rgb(84, 50, 16));
        paint.setTextSize(25);
        canvas.drawText(character, x-10, y+5, paint);
    }
}
