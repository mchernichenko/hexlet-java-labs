package exercise;

class Point {
    // BEGIN
    private static int x;
    private static int y;

    public static Point makePoint(int coordX, int coordY) {
        Point.x = coordX;
        Point.y = coordY;
        return new Point();
    }

    public static int getX(Point point) {
        return point.x;
    }

    public static int getY(Point point) {
        return point.y;
    }

    public static String pointToString(Point point) {
        return "(" + point.x + ", " + point.y + ")";
    }

    public static int getQuadrant(Point point) {
        if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else if (x > 0 && y < 0) {
            return 4;
        } else {
            return 0;
        }
    }
    // END
}
