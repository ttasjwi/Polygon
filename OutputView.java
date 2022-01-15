import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 24;

    public static void print(Shape shape) {
        printGraph(shape);
        printStatus(shape);
    }

    public static void printStatus(Shape shape) {
        if (shape instanceof Point) {
            Point p = (Point) shape;
            System.out.println("점 "+p);
        } else if (shape instanceof Segment) {
            Segment segment = (Segment) shape;
            System.out.println("선분의 길이 : "+segment.length());
        } else {
            System.out.println(shape.countOfPoints()+"각형의 넓이 : "+ shape.area());
        }
    }

    public static void printGraph(Shape shape) {
        Map<Point, String> graph = prepareGraph();
        putPointsToGraph(graph, shape);
        String graphString = toGraphString(graph);
        System.out.println(graphString);
    }

    private static String toGraphString(Map<Point, String> graph) {
        return graph.entrySet().stream()
                .sorted(Comparator.<Map.Entry<Point, String>>comparingInt(e -> e.getKey().getY())
                        .reversed()
                        .thenComparing(e -> e.getKey().getX()))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(""));
    }

    private static void putPointsToGraph(Map<Point, String> graph, Shape shape) {
        for (Point point : shape.getPoints()) {
            if (point.getX() == MAX_COORDINATE) {
                graph.put(point, "⚬ \n");
            } else {
                graph.put(point, "⚬ ");
            }
        }
    }

    private static Map<Point, String> prepareGraph() {
        Map<Point, String> graph = new HashMap<>();
        fillSpace(graph);
        fillBorder(graph);
        fillAxisNumber(graph);
        return graph;
    }

    private static void fillAxisNumber(Map<Point, String> graph) {
        fillXAxisNumber(graph);
        fillYAxisNumber(graph);
    }

    private static void fillYAxisNumber(Map<Point, String> graph) {
        for(int y=MIN_COORDINATE ; y<=MAX_COORDINATE; y++) {
            if (y%2 == 0 && y!=0) {
                Point leftNumberPoint = new Point(-1,y);
                String numberString = String.format("%2d",y);
                graph.put(leftNumberPoint, numberString);
            } else {
                Point point = new Point(-1,y);
                graph.put(point, "  ");
            }
        }
    }

    private static void fillXAxisNumber(Map<Point, String> graph) {
        for(int x=MIN_COORDINATE ; x<=MAX_COORDINATE; x++) {
            if (x%2 == 0) {
                Point underNumberPoint = new Point(x,-1);
                String numberString = (x<10) ? String.format("%d   ",x) : String.format("%d  ",x);
                graph.put(underNumberPoint, numberString);
            }
        }
    }

    private static void fillBorder(Map<Point, String> graph) {
        drawXAxis(graph);
        drawYAxis(graph);
    }

    private static void drawXAxis(Map<Point, String> graph) {
        for (int x=MIN_COORDINATE; x<=MAX_COORDINATE; x++) {
            Point point = PointFactory.makePoint(x,0);
            if (x==MAX_COORDINATE) {
                graph.put(point, "_ \n");
            } else {
                graph.put(point, "_ ");
            }
        }
    }

    private static void drawYAxis(Map<Point, String> graph) {
        for (int y=MIN_COORDINATE; y<=MAX_COORDINATE; y++) {
            Point point = PointFactory.makePoint(0,y);
            graph.put(point, "| ");
        }
    }

    private static void fillSpace(Map<Point, String> graph) {
        for (int y=MIN_COORDINATE; y<=MAX_COORDINATE; y++) {
            for (int x=MIN_COORDINATE; x<=MAX_COORDINATE; x++) {
                Point point = PointFactory.makePoint(x,y);
                if (x== MAX_COORDINATE) {
                    graph.put(point, "  \n");
                } else {
                    graph.put(point, "  ");
                }
            }
        }
    }

}
