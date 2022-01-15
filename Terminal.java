import java.util.List;

public class Terminal {

    public boolean run() {
        try {
            List<Point> points = InputView.input();
            Shape shape = ShapeFactory.makeShape(points);
            OutputView.print(shape);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return run();
        }
    }
}
