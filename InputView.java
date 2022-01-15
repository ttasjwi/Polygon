import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final String PROMPT = "> 좌표를 입력하세요.";
    private static final Scanner SCAN_INSTANCE = new Scanner(System.in);
    private static final Pattern INPUT_SPLIT_PATTERN = Pattern.compile("[-]?\\(([-]?\\d+),([-]?\\d+)\\)");

    private static Scanner prepareInput() {
        return SCAN_INSTANCE;
    }

    public static List<Point> input() {
        Scanner scanner = prepareInput();
        printPrompt();
        String inputLine = scanner.nextLine();
        try {
            List<Point> points = parseToPoints(inputLine);
            return points;
        } catch (IllegalArgumentException ile) {
            System.out.println(ile.getMessage());
            return input();
        } catch (Exception e) {
            e.printStackTrace();
            return input();
        }
    }

    private static void printPrompt() {
        System.out.println(PROMPT);
    }

    private static List<Point> parseToPoints(String inputLine) {
        List<Point> points = new ArrayList<>();
        Matcher matcher = INPUT_SPLIT_PATTERN.matcher(inputLine);

        while(matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            Point point = PointFactory.makePoint(x, y);
            points.add(point);
        }
        if (points.size() == 0)
            throw new IllegalArgumentException("패턴에 매칭되는 점이 존재하지 않습니다. - 패턴 : (숫자, 숫자)");
        return points;
    }
}
