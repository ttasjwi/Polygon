import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PointTest {

    @Test
    @DisplayName("getX, getY 테스트")
    void getterTest() {

        // given
        Point p = new Point(3,4);

        // when
        int x = p.getX();
        int y = p.getY();

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(x).isEqualTo(3);
        softAssertions.assertThat(y).isEqualTo(4);
        softAssertions.assertAll();
    }


    @Test
    @DisplayName("Point.distance(x,y) 테스트")
    void distanceTest1() {

        // given
        Point p = new Point(0,0);
        Point q = new Point(5,12);

        // when
        double distance = Point.distance(p,q);

        // then
        assertThat(distance).isEqualTo(13);
    }

    @Test
    @DisplayName("distance 메서드가 정상적으로 값을 반환하는지 테스트")
    void distanceTest2() {

        // given
        Point p = new Point(0,0);
        Point q = new Point(5,12);

        // when
        double distance1 = p.distance(q);
        double distance2 = Point.distance(p,q);

        // Then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(distance1).isEqualTo(distance2);
        softAssertions.assertThat(distance2).isEqualTo(13);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("equals가 정확한 boolean 값을 반환하는지 테스트")
    void equalsTest() {
        // given
        Point p = new Point(0,0);
        Point q = new Point(0,0);
        Point r = new Point(0,1);
        Point s = new Point(1,0);
        Point t = new Point(1,1);

        // when
        boolean equals1 = p.equals(q);
        boolean equals2 = p.equals(r);
        boolean equals3 = p.equals(s);
        boolean equals4 = p.equals(t);

        // then
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(equals1).isTrue();
        softAssertions.assertThat(equals2).isFalse();
        softAssertions.assertThat(equals3).isFalse();
        softAssertions.assertThat(equals4).isFalse();

        softAssertions.assertAll();
    }

    @Test
    @DisplayName("hashCode를 좌표에 따라 반환하는지 테스트")
    void hashCodeTest() {
        // given
        Point p = new Point(0,0);
        Point q = new Point(0,0);
        Point r = new Point(0,1);
        Point s = new Point(1,0);
        Point t = new Point(1,1);

        // when
        int hashCodeP = p.hashCode();
        int hashCodeQ = q.hashCode();
        int hashCodeR = r.hashCode();
        int hashCodeS = s.hashCode();
        int hashCodeT = t.hashCode();

        // then
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(hashCodeP).isEqualTo(hashCodeQ);
        softAssertions.assertThat(hashCodeP).isNotEqualTo(hashCodeR);
        softAssertions.assertThat(hashCodeP).isNotEqualTo(hashCodeS);
        softAssertions.assertThat(hashCodeP).isNotEqualTo(hashCodeT);

        softAssertions.assertAll();
    }
}