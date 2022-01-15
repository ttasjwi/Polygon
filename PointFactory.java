
public class PointFactory {

    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 24;

    private static boolean isValidCoordinate (int x, int y) {
        return (MIN_COORDINATE <= x && x <= MAX_COORDINATE)
                && (MIN_COORDINATE <= y && y <= MAX_COORDINATE);
    }

    private static void coordinateValidation(int x, int y) {
        if (!isValidCoordinate(x,y))
            throw new IllegalArgumentException
                    (String.format
                            ("유효하지 않은 좌표값이 존재합니다. 좌표의 유효 범위 : %d ~ %d",
                                    MIN_COORDINATE, MAX_COORDINATE)
                    );
    }

    public static Point makePoint(int x, int y) {
        coordinateValidation(x,y);
        return new Point(x,y);
    }
}
