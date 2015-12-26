package com.example.ilnarsabirzyanov.areena.game;

/**
 * Created by Cawa on 24.12.2015.
 */
public class Line {
    double a, b, c;
    Line() {
    }

    public Line(Point p1, Point p2) {
        a = (p1.x - p2.x);
        b = ( - p1.y + p2.y);
        c = - a * p1.y - b * p1.x;
    }

    public Point per(Line l) {
        double d0 = a*l.b - b * l.a;
        double d1 = c*l.b - b * l.c;
        double d2 = a*l.c - c * l.a;
        return new Point(-d2/d0, -d1/d0);
    }
}
