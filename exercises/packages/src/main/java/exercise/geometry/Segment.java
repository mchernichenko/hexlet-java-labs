package exercise.geometry;

// BEGIN
public class Segment {
    public static double[][] makeSegment(double[] point1, double[] point2) {
        return new double[][]{{Point.getX(point1), Point.getY(point1)}, {Point.getX(point2), Point.getY(point2)}};
    }

    public static double[] getBeginPoint(double[][] segment) {
        return segment[0];
    }

    public static double[] getEndPoint(double[][] segment) {
        return segment[1];
    }
}
// END
