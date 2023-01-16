package exercise;

import exercise.geometry.Point;

public class App {
    public static double[] getMidpointOfSegment(double[][] segment) {
        return new double[]{(segment[0][0] + segment[1][0]) / 2, (segment[0][1] + segment[1][1]) / 2};
    }

    public static double[][] reverse(double[][] segment) {
        double[] beginPoint = Point.makePoint(segment[1][0], segment[1][1]);
        double[] endPoint = Point.makePoint(segment[0][0], segment[0][1]);
        return new double[][]{beginPoint, endPoint};
    }

    public static boolean isBelongToOneQuadrant(double[][] segment) {
        return (Math.signum(segment[0][0]) * Math.signum(segment[1][0]) > 0
             && Math.signum(segment[0][1]) * Math.signum(segment[1][1]) > 0);
    }
}
// END
