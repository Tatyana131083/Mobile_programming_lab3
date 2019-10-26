package com.example.jumpingobjectv3;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class BounceView extends View {
    private ArrayList<Ball> mBalls;
    private int screenX;
    private int screenY;
    private String character;
    private int number;

    public BounceView(Context context, int screenX, int screenY){
        super(context);
        mBalls = new ArrayList<>();
        this.screenX = screenX;
        this.screenY = screenY;
        number = 0;
    }

    @Override
    protected void onDraw(final Canvas canvas){
        for (final Ball ball: mBalls){
            new Runnable() {
                @Override
                public void run() {
                    if (ball.getALife()) {
                        ball.move();
                        ball.draw(canvas);
                    }
                }}.run();
        }
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                number++;
                if (number > 6){
                    number = number % 6;
                }
                definitionCharacter(number);
                mBalls.add(new Ball((int)event.getX(), (int)event.getY(),
                        screenX, screenY, character));
                break;
            default:
                break;
        }
        return true;
    }

    private boolean definitionCharacter(int number){
        switch (number){
            case 1: character = "Я";
                    break;
            case 2:
            case 3: character = "Л";
                break;
            case 4: character = "И";
                break;
            case 5: character = "Н";
                break;
            case 0:
            case 6: character = "А";
                break;
            default:
                break;
        }
        return  true;
    }
}
