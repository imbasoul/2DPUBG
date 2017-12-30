package yy1020;

import static java.lang.Math.sqrt;

/**
 * Created by Howard on 2017/12/29.
 */
public class Poi {
    public double x, y;
    Poi() {
        x = y = 0.0;
    }
    Poi(double a, double b) {
        x = a;
        y = b;
    }

    public double dis(Poi a) {
        return sqrt(sqr(a.x - x) + sqr(a.y - y));
    }

    private double sqr(double v) {
        return v * v;
    }
}
