import java.util.Objects;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector positionVector() {
        return Vector.of(this.x, this.y);
    }

    // 두 점 사이의 거리
    public static double distance(Point p, Point q) {
        Vector pq = Vector.fromTo(p,q);
        return pq.size();
    }

    // 다른 점과의 거리
    public double distance(Point other) {
        return distance(this, other);
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
        return String.format("(%d,%d)",x,y);
    }

}