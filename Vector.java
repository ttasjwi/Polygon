import java.util.Objects;

import static java.lang.Math.*;

public class Vector {

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

    // 벡터의 크기
    public static double size(Vector vector) {
        return sqrt(pow(vector.x, 2) + pow(vector.y, 2));
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

    public static boolean isRightAngle(Vector v1, Vector v2) {
        return innerProduct(v1,v2) == 0;
    }

    public Vector sum(Vector other) {
        return sum(this, other);
    }

    public Vector subtract(Vector other) {
        return subtract(this,other);
    }

    public Vector multiply(int value) {
        return Vector.multiply(this, value);
    }

    public int innerProduct(Vector other) {
        return Vector.innerProduct(this, other);
    }

    public double cosine(Vector other) {
        return Vector.cosine(this,other);
    }

    public double size() {
        return Vector.size(this);
    }

    public boolean isPararell(Vector other) {
        return isPararell(this, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector other = (Vector) o;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",this.x,this.y);
    }

}
