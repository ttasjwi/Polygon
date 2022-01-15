import java.util.ArrayList;
import java.util.List;

public class Segment implements Shape {

    private List<Point> points = new ArrayList<>();

    public Segment(List<Point> points) {
        this.points.addAll(points);
    }

    private Point start() {
        return points.get(0);
    }

    private Point end() {
        return points.get(1);
    }

    public double length() {
        return Point.distance(start(), end());
    }

    public double area() {
        return 0;
    }

    @Override
    public int countOfPoints() {
        return points.size();
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }

}
