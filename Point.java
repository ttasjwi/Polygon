import java.util.Objects;
import static java.lang.Math.*;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        return distance(this, other);
    }

    public static double distance(Point p, Point q) {
        return sqrt(
                pow(distanceX(p,q),2) + pow(distanceY(p,q),2)
        );
    }

    private static int distanceX(Point p, Point q) {
        return abs(p.getX()-q.getX());
    }

    private static int distanceY(Point p, Point q) {
        return abs(p.getY()-q.getY());
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}