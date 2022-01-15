import java.util.ArrayList;
import java.util.List;

public class Polygon implements Shape {

    private List<Point> points = new ArrayList<>();

    public Polygon(List<Point> points) {
        this.points.addAll(points);
    }

    @Override
    public double area() {
        double sum = 0;
        for (int index = 0; index<points.size(); index++) {
            Vector start = Point.positionVector(points.get(index));
            Vector end =
                    (index != points.size()-1)
                            ? Point.positionVector(points.get(index+1))
                            : Point.positionVector(points.get(0));
            sum += Vector.crossProduct(start, end);
        }
        return Math.abs(sum * 0.5);
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
