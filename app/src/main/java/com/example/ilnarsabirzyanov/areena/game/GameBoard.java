package com.example.ilnarsabirzyanov.areena.game;

import android.graphics.Canvas;

import com.example.cawa.compas.R;

import java.util.Vector;

/**
 * Created by Cawa on 22.12.2015.
 */
public class GameBoard {
    int TIMEOUT = 60*60;
    double BALLR = 20;
    int MAXUTSIZE = 100;
    int MAXUTLENGTH = 750;
    int MAXUTDL = 200;

    double prevTime;
    double dt = 1;
    boolean valid;
    double sizeX, sizeY, centerX, centerY;
    Ball ball;
    Vector<Trace> traces;
    Trace lastTrace;
    public GameBoard() {
        ball = new Ball(centerX, centerY, BALLR);
        lastTrace = new Trace();
        traces = new Vector<>();
    }

    public void setCoord(int x, int y) {
        sizeX = x;
        sizeY = y;
        centerX = x / 2;
        centerY = y / 2;
        ball = new Ball(centerX, centerY, BALLR);
        lastTrace = new Trace(MAXUTSIZE, MAXUTLENGTH, MAXUTDL);
    }

    public void setDifficulty(int par) {
        lastTrace.setDiff(par);
        ball.setDiff(par);
        switch (par) {
            default:
                Trace trace = new Trace(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
                trace.addPoint(0, 0, Integer.MAX_VALUE);
                trace.addPoint(sizeX, 0, Integer.MAX_VALUE);
                trace.addPoint(sizeX, sizeY, Integer.MAX_VALUE);
                trace.addPoint(0, sizeY, Integer.MAX_VALUE);
                trace.addPoint(0, 0, Integer.MAX_VALUE);
                traces.add(trace);
        }
    }

    public void addPoint(double x, double y, double t) {
        lastTrace.addPoint(x, y, t + TIMEOUT);
        prevTime = t;
        if ((ball.c.x < 0) || (ball.c.y < 0) || (ball.c.x > sizeX) || (ball.c.y > sizeY)) {
            ball.c = new Point(centerX, centerY);
        }
    }

    public void draw(Canvas canvas, int time) {/*
        for (int i = 0; i < traces.size(); i++) {
            traces.get(i).check(time);
        }*/
        lastTrace.check(time);
        calculateBall(time);

        for (int i = 0; i < traces.size(); i++) {
            traces.get(i).draw(canvas);
        }

        lastTrace.draw(canvas);
        ball.draw(canvas);
    }

    void calculateBall(int time) {
        //ball = lastTrace.calc(ball);
        ball.e = ball.v;
        while (!ball.e.equals(new Point(0, 0))) {
            Point p1 = ball.c;
            Point p2 = ball.c.add(ball.e);
            Point p3 ;
            Point p4;
            Point pa1 = null, pa2 = null;
            Point param = null;
            double t = -1;
            int j = -1;
            if (lastTrace.points.size() >= 2) {
                p3 = lastTrace.points.get(0).point;
                for (int i = 1; i < lastTrace.points.size(); i++) {
                    p4 = lastTrace.points.get(i).point;
                    double tmp = GameUtils.getTme(p1, p2, p3, p4, ball.r + GameUtils.w);
                    if (tmp <= 1) {
                        if (t == -1) {
                            pa1 = p3;
                            pa2 = p4;
                            t = tmp;
                            j = i;
                        } else {
                            if (t > tmp) {
                                t = tmp;
                                j = i;
                                pa1 = p3;
                                pa2 = p4;
                            }
                        }
                    }
                    p3 = p4;
                }
            }
            for (int k = 0; k < traces.size(); k++) {
                Trace trace = traces.get(k);
                p3 = trace.points.get(0).point;
                if (trace.points.size() >= 2) {
                    for (int i = 1; i < trace.points.size(); i++) {
                        p4 = trace.points.get(i).point;
                        double tmp = GameUtils.getTme(p1, p2, p3, p4, ball.r + GameUtils.w);
                        if (tmp <= 1) {
                            if (t == -1) {
                                pa1 = p3;
                                pa2 = p4;
                                t = tmp;
                                j = i;
                            } else {
                                if (t > tmp) {
                                    t = tmp;
                                    j = i;
                                    pa1 = p3;
                                    pa2 = p4;
                                }
                            }
                        }
                        p3 = p4;
                    }
                }
            }
            if (j == -1) {
                ball.c = ball.c.add(ball.e);
                return;
            } else {
                ball = GameUtils.bounce(ball, pa1, pa2, t);
            }
        }
    }

}
