package exercise;

// BEGIN
interface Home extends Comparable<Home> {
    double getArea();

    String toString();

    int compareTo(Home home);
}
// END
