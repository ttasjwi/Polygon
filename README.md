# 다각형

---

## Main 클래스
- Terminal의 run()메서드를 호출한다.

---

## Terminal 클래스
```
    public boolean run() {
        try {
            List<Point> points = InputView.input();
            Shape shape = ShapeFactory.makeShape(points);
            OutputView.print(shape);
            return true;
        } catch (IllegalArgumentException ile) {
            System.out.println(ile.getMessage());
            return run();
        }catch (Exception e) {
            e.printStackTrace();
            return run();
        }
    }
```
- InputView의 input() 메서드를 호출하여 points들을 전달받는다.
- ShapeFactory의 makeShape 메서드를 호출하고, points를 전달한뒤 shape를 반환받는다.
- OutputView의 print 메서드를 호출하여 결과를 출력한다. 
- 잘못된 인자가 들어왔을 시 , IllegalArgumentException으로 처리
- 그 외 예상치 못 한 예외들은 Exception을 통해 받아 `printStackTrace()`로 출력하도록 함.

---

## Point 클래스
```
public class Point implements Shape {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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

    public static Vector positionVector(Point point) {
        return Vector.of(point.x, point.y);
    }

    public Vector positionVector() {
        return positionVector(this);
    }
```
점에 관한 정보를 반환하는 클래스
- 두 점 사이의 거리(distance) : 두 점의 위치벡터의 차에 해당하는 벡터의 크기
- 위치벡터(positionVector) : 원점으로부터 그 점까지 향한 벡터

---

## Vector 클래스
```
    private int x;
    private int y;

    private static final Vector ZERO_VECTOR = new Vector(); // 영벡터

    private Vector() {}

    private Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector of(int x, int y) {
        return (x == 0 && y == 0) ? zero() : new Vector(x,y);
    }

    private static Vector zero() {
        return ZERO_VECTOR;
    }
```
- Vector는 크기와 방향을 가진 물리량이다.
  - 시점과 종점이 일치하는 벡터를 영벡터라고 칭한다.
  - 영벡터는 크기가 0이고 방향이 존재하지 않는다.
  - 방향과 크기 같으면 같은 벡터다.
- 시점과 종점 사이의 거리를 벡터의 크기로 칭한다.
- 시점이 원점인 벡터를 위치벡터라고 하고, 이 클래스는 위치벡터를 칭하도록 한다.
```
    // 벡터의 합
    public static Vector sum(Vector v1, Vector v2) {
        int xSum = v1.x + v2.x;
        int ySum = v1.y + v2.y;

        return (xSum == 0 && ySum == 0) ? zero() : new Vector(xSum, ySum);
    }

    // 벡터의 차
    public static Vector subtract(Vector v1, Vector v2) {
        int xSubtract = v1.x - v2.x;
        int ySubtract = v1.y - v2.y;
        return (xSubtract == 0 && ySubtract == 0) ? zero() : new Vector(xSubtract, ySubtract);
    }

    // 벡터의 정수배
    public static Vector multiply(Vector v, int scale) {
        int xMultiply = v.x * scale;
        int yMultiply = v.y * scale;

        return (xMultiply == 0 && yMultiply == 0) ? zero() : new Vector(xMultiply, yMultiply);
    }

    // 벡터의 내적
    public static int innerProduct(Vector v1, Vector v2) {
        int xProduct = v1.x * v2.x;
        int yProduct = v1.y * v2.y;
        return xProduct + yProduct;
    }

    // 두 벡터의 코사인값(이때 두 벡터가 이루는 각의 크기는 0 이상 180도 이하이다.)
    public static double cosine(Vector v1, Vector v2) {
        return ((double)innerProduct(v1,v2))/(v1.size() * v2.size());
    }

    public static double crossProduct(Vector v1, Vector v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }

    public static double crossProductSize(Vector v1, Vector v2) {
        return abs(crossProduct(v1,v2));
    }

    // 두 벡터의 사인값(이떄 두 벡터가 이루는 각의 크기는 0 이상 180도 이하이다.
    public static double sine(Vector v1, Vector v2) {
        return (crossProductSize(v1,v2))/(v1.size() * v2.size());
    }
```
- 벡터의 기본연산 : 합,차,정수배, 내적, 외적
  - 내적 : 두 백터의 같은 방향 스칼라곱
  - 외적 : 이 프로그램에서는 다음 crossProduct의 z성분의 실수값을 칭하도록 한다.
    - 예> (x1,y1,0) (x2,y2,0) -> crossProduct : (0,0,x1y2-y1x2)
  - 외적의 크기 : 두 벡터의 시점을 일치시켰을 때 확장한 평행사변형 면적
    - 즉, crossProduct의 1/2은 두 벡터의 사이 삼각형 면적의 면적이다.
  - 두 벡터가 이루는 각의 cosine 값 : innerProduct를 두 벡터의 크기 곱으로 나누면 된다.
  - 두 벡터가 이루는 각의 sine 값 : crossProduct의 크기를 두 벡터의 크기 곱으로 나누면 된다.


- 평행성 여부
    ```
    // 두 벡터가 평행한 지의 여부
    public static boolean isPararell(Vector vector1, Vector vector2) {
    
        // 어느 한 쪽이라도 영벡터면 평행하다.
        if (vector1 == zero() || vector2 == zero()) return true;

        int x1 = vector1.x;
        int y1 = vector1.y;

        int x2 = vector2.x;
        int y2 = vector2.y;

        if (x1 == 0) { // y축
            return x2==0;
        } else if (y1 == 0) { // x축
            return y2==0;
        } else { // 실수배가 가능할 때
            return x1 * y2 == x2 * y1;
        }
    
    }
    ```
    - 두 벡터가 실수배 관계이면 평행하다.


- 직각 관계
    ```
    public static boolean isRightAngle(Vector v1, Vector v2) {
        return innerProduct(v1,v2) == 0;
    }
    ```
- 두 벡터의 내적값이 0이면 직각이다.
---

## InputView
```
    private static final Pattern INPUT_SPLIT_PATTERN = Pattern.compile("[-]?\\(([-]?\\d+),([-]?\\d+)\\)");

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
```
- 정규표현식을 기반으로 Pattern 객체를 생성. pattern과 입력문자열을 통해 matcher를 생성하여 (숫자,숫자)를 감지한다.
  - 내부의 숫자들은 그룹화되어있어서 바로 Matcher의 group 메서드를 사용하여 꺼낼 수 있다.
- 복잡하게 분기문을 처리하지 않고 최소한의 예외처리를 수행할 수 있다.

---

## PointFactory

```

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

```
- Point 객체를 바로 생성할 수 있으나, 문제상황에서 0~24 범위 내에서 Point를 생성해야해서 도형을 생성하는 상황에선 별도의 PointFactory 객체를 생성하도록 했다.

---

## Shape 인터페이스

```
import java.util.List;

public interface Shape {

    double area();
    int countOfPoints();
    List<Point> getPoints();
}

```
- area: 면적
- countOfPoints : 점의 갯수
- getPoints : 도형에 포함된 Point들을 List로 반환

---

## Shape Factory
```
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

```
- Shape 생성 담당
- duplicateValidation : point들 중에 중복되는 점이 있는지 예외처리
- pararellValidation : 입력받은 point들을 순차적으로 비교하여 연속된 세 점이 평행한 직선상에 존재하는 점인지 validation

---

## Segment

```
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
```
- 점이 두개일 때 선분

---

## Polygon

```
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
```

- 점이 3개 이상일 때 다각형(Polygon)
- area : 모든 다각형에 대해 면적을 구할 수 있음.
  - 연속된 각 벡터들의 crossProduct 값의 합을 구한 뒤, 이를 반으로 나누고 절댓값 처리 하면 면적이다. 
  - 볼록, 오목 다각형 상관없이 면적을 구할 수 있음
  - (한계) 선분과 선분이 교차하는 상황에 대해서는 정확한 면적 출력을 보장하지 못 한다.

---

## OutputView

- Shape 기반으로 출력 담당
- `Map<Point, String>` 기반으로 출력

---