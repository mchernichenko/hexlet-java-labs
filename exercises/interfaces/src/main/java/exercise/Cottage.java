package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home home) {
        return Double.compare(this.getArea(), home.getArea());
    }

    @Override
    public String toString() {
        return "%d этажный коттедж площадью %.1f метров".formatted(floorCount, getArea());
    }
}
// END
