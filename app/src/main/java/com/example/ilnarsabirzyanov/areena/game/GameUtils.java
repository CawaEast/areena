package com.example.ilnarsabirzyanov.areena.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.ilnarsabirzyanov.areena.GameView;

/**
 * Created by Cawa on 22.12.2015.
 */
public class GameUtils {
    public static double EPS = 0.000001;
    public static double FPS = 60;
    public static double w = 0;
    public static void drawTrace(Canvas canvas, Trace trace) {
        Paint paint =  new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        for (int i = 1; i < trace.points.size(); i++) {
            canvas.drawLine((float) trace.points.get(i - 1).point.x, (float)trace.points.get(i - 1).point.y,
                    (float)trace.points.get(i).point.x, (float)trace.points.get(i).point.y, paint);
        }

    }
    public static void drawBall(Canvas canvas, Ball ball) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        canvas.drawCircle((float) ball.c.x, (float) ball.c.y, (float) ball.r, paint);
    }

    public static void drawCBall(Canvas canvas, CBall ball) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(1);
        canvas.drawCircle((float) ball.c.x, (float) ball.c.y, (float) ball.r, paint);
    }


    public static void setListener(final GameView listener) {
        listener.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                listener.onMyTouch(event);
                return true;
            }
        });
    }

    public static double getDist(Point a, Point b, Point c) {
        double ans = Math.min(a.sub(c).mod2(), b.sub(c).mod2());
        return checkedDist(a, b, c, Math.sqrt(ans));
    }

    public static double checkedDist(Point a, Point b, Point c, double ans) {
        if (a.sub(b).scal(a.sub(c))*a.sub(b).scal(b.sub(c)) < 0) {
            return Math.min(dist(a, b, c), ans);
        }
        return ans;
    }

    public static double dist(Point a, Point b, Point c) {
        Point abV = b.sub(a);
        Point acV = c.sub(a);
        double angle = 1 - abV.cos2(acV);
        return Math.sqrt(angle*acV.mod2());
    }

    public static Ball bounce(Ball ball, Point a, Point b, double t) {
        boolean tmp = true;
        if (getDist(a, b, ball.c) > ball.r + w) {
            ball.c = ball.c.add(ball.e.mul(t));
            ball.e = ball.e.mul(1 - t);
            tmp = false;
        }
        if (t > 1) {
            t = 1;
        }
        double d = dist(a, b, ball.c);
        double cd = checkedDist(a, b, ball.c, d);
        if (d != cd) {
            ball.e.neg();
            ball.v.neg();
            return ball;
        }
        Point ac = ball.c.sub(a);
        Point bc = ball.c.sub(b);
        Point ab = b.sub(a);
        double alpha = 1 - ab.cos2(ball.e);
        Point addV = new Point(ab.y, -ab.x);
        Point addE = new Point(addV);
        double k = (ball.v.mod2() * alpha) * 4;
        k/= addV.mod2();
        addV = addV.mul(Math.sqrt(k));
        k = (ball.e.mod2() * alpha) * 4;
        k/= addE.mod2();
        addE = addE.mul(Math.sqrt(k));
        if (addE.scal(ball.e) > 0) {
            addE.neg();
            addV.neg();
        }
        ball.e = ball.e.add(addE);
        ball.v = ball.v.add(addV);
        if (tmp) {
            ball.c = ball.c.add(ball.e.mul(t));
            ball.e = ball.e.mul(1 - t);
        }
        if (ball.v.mod2() > 1) {
            //k *= ball.speed;
            ball.v = ball.v.mul(ball.speed/Math.sqrt(ball.v.mod2()));
        }
        return ball;
    }

    public static double distP(Point a, Point b) {
        return Math.sqrt(a.sub(b).mod2());
    }

    public static double getTme(Point a, Point b, Point c, Point d, double rad) {
        Point ad = d.sub(a);
        Point ab = b.sub(a);
        Point ac = c.sub(a);
        Point cd = d.sub(c);
        Line l1 = new Line(a, b);
        Line l2 = new Line(c, d);
        if (l1.a * l2.b == l2.a * l1.b) {// if lines are parallel
            return 9999999;
        }
        Point per = l1.per(l2);
        double t = 9999999;
        double t1 = per.sub(a).x / b.sub(a).x;
        if ((t1 > 0) && (c.sub(per).scal(d.sub(per)) < 0)) {
            Point addV = new Point(cd.y, -cd.x);
            double k = dist(c, d, a);
            k *= k;
            k /= addV.mod2();
            addV = addV.mul(Math.sqrt(k));
            t = Math.abs((Math.sqrt(addV.mod2()) - rad) / (addV.scal(ab) / Math.sqrt(addV.mod2())));
            Point cc = a.add(ab.mul(t));
            if (cd.scal(cc.sub(c)) * cd.scal(cc.sub(d)) >= 0) {
                t = 999999;
            }
            if (t < 1) {
                t *= 1.0;
            }
        }
        if (t < 1) {
            t *= 1;
        }
        double t2 = (Math.sqrt(ad.mod2()) - rad)/(ad.scal(ab) / Math.sqrt(ad.mod2()));
        if (t2 > 0) {
            t2 = Math.sqrt(t2);
            t = Math.min(t, t2);
        }
        double t3 = (Math.sqrt(ac.mod2()) - rad)/(ac.scal(ab) / Math.sqrt(ac.mod2()));
        if (t3 > 0) {
            t3 = Math.sqrt(t3);
            t = Math.min(t, t3);
        }
        if ((t <= 0) || (getDist(c, d, b) > rad)) {
            return 99999;
        }

        return t;
    }
}
