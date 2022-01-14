import java.util.Objects;

public class Point {

    private Vector position;

    public Point(int x, int y) {
        this.position = Vector.of(x,y);
    }

    // 두 점 사이의 거리
    public static double distance(Point p1, Point p2) {
        Vector subtract = Vector.subtract(p1.position, p2.position);
        return subtract.size(); // 벡터의 차를 구하고, 그 크기를 반환
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
        return position.equals(point.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

}