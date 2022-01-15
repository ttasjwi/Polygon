import java.util.List;

public class ShapeFactory {

    public static Shape makeShape(List<Point> points) {
        duplicateValidation(points);

        switch(points.size()) {
            case 0 :
                throw new IllegalArgumentException("점이 존재하지 않습니다.");
            case 1 :
                return popPoint(points);
            case 2 :
                return makeSegment(points);
            default :
                return makePolygon(points);
        }
    }

    private static void duplicateValidation(List<Point> points) {
        long countOfDiffPoints = points.stream().distinct().count();
        if (countOfDiffPoints != points.size()) throw new IllegalArgumentException("중복되는 점이 존재합니다.");
    }

    private static Point popPoint(List<Point> points) {
        return points.get(0);
    }

    private static Segment makeSegment(List<Point> points) {
        return new Segment(points);
    }

    private static Polygon makePolygon(List<Point> points) {
        pararellValidation(points);
        return new Polygon(points);
    }

    private static void pararellValidation(List<Point> points) {
        Point start = points.get(0);
        Point end = points.get(1);
        Vector currentVector = Vector.fromTo(start, end);
        Vector beforeVector;

        for (int index = 1; index< points.size()-1; index++) {
            beforeVector = currentVector;
            start = end;
            end = points.get(index+1);
            currentVector = Vector.fromTo(start,end);
            if (beforeVector.isPararell(currentVector)) throw new IllegalArgumentException("연속된 세 점이 일 직선상에 존재합니다.");
        }

    }
}
