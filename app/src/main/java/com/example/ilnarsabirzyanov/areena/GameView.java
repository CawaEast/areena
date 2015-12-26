package com.example.ilnarsabirzyanov.areena;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.ilnarsabirzyanov.areena.game.GameBoard;
import com.example.ilnarsabirzyanov.areena.game.GameUtils;

/**
 * Created by Cawa on 30.11.2015.
 */
public class GameView extends View {
    boolean NEEDTOSETCOORD = true;
    boolean pause = false;
    int time = 0;
    GameBoard game;

    public GameView(Context context, AttributeSet attributes) {
        super(context, attributes);

        GameUtils.setListener(this);
        game = new GameBoard();

    }

    public boolean onMyTouch(MotionEvent event) {
        if (!pause) {
            game.addPoint(event.getX(), event.getY(), time);
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (NEEDTOSETCOORD) {
            game.setCoord(getWidth(), getHeight());
            NEEDTOSETCOORD = false;
            game.setDifficulty(1);
        }
        if (!pause) {
            time++;
        }
        boolean end = game.draw(canvas,time, pause);
        if (end) {
            pause = true;
            gameOver(time, game.getScore());
        }
        invalidate();
    }

    // here you can invoke some metods connected with ending of game
    public void gameOver(double time, int score) {
        time /= GameUtils.FPS;


    }

}
