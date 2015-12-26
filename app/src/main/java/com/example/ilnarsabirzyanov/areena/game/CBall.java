package com.example.ilnarsabirzyanov.areena.game;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Cawa on 26.12.2015.
 */
public class CBall {
    Point c;
    double r;
    int p;
    CBall(double mX, double mY, double rad) {
        Random gen = new Random();
        c = new Point(gen.nextInt((int)mX), gen.nextInt((int)mX));
        r = rad;
        p = 10;
    }

    public void draw(Canvas canvas) {
        GameUtils.drawCBall(canvas, this);
    }
}
