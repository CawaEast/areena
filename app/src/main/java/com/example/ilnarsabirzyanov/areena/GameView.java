package com.example.ilnarsabirzyanov.areena;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.cawa.compas.game.GameBoard;
import com.example.cawa.compas.game.GameUtils;

/**
 * Created by Cawa on 30.11.2015.
 */
public class GameView extends View {
    boolean NEEDTOSETCOORD = true;
    int time = 0;
    GameBoard game;

    public GameView(Context context, AttributeSet attributes) {
        super(context, attributes);

        GameUtils.setListener(this);
        game = new GameBoard();

    }

    public boolean onMyTouch(MotionEvent event) {
        game.addPoint(event.getX(), event.getY(), time);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (NEEDTOSETCOORD) {
            game.setCoord(getWidth(), getHeight());
            NEEDTOSETCOORD = false;
            game.setDifficulty(0);
        }

        time++;
        game.draw(canvas, time);
        invalidate();
    }


}
