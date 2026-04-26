package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    //Тесты на площадь

    @Test //Тест на площадь равносторонний
    void canCalculateArea() {
        var s = new Triangle(5.0, 5.0, 5.0);
        double result = s.area();
        Assertions.assertEquals(10.83, result, 0.01);
    }

    @Test //Тест на площадь прямоугольный
    void canCalculateArea2() {
        var s = new Triangle(3.0, 4.0, 5.0);
        double result = s.area();
        Assertions.assertEquals(6.0, result);
    }



    //Тесты на периметр

    @Test //Периметр равностороннего треугольника
    void canCalculatePerimeter1() {
        var s = new Triangle(5.0, 5.0, 5.0);
        double result = s.perimeter();
        Assertions.assertEquals(15.00, result);
    }

    @Test //Периметр прямоугольного треугольника
    void canCalculatePerimeter2() {
        var s = new Triangle(3.0, 4.0, 5.0);
        double result = s.perimeter();
        Assertions.assertEquals(12.00, result);
    }


    //Тесты на положительность сторон

    @Test // a < 0
    void cannotCreateTriangleWithNegativeSideA() {
        try {
            new  Triangle (-5.0, 3.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // ok
        }
    }

    @Test // b < 0
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new  Triangle (5.0, -3.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // ok
        }
    }

    @Test // c < 0
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new  Triangle (5.0, 3.0, -4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // ok
        }
    }


    //Тесты на существование треугольника

    @Test // a = b + c
    void cannotCreateTriangleWithSideA() {
        try {
            new  Triangle (7.0, 3.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // ok
        }
    }

    @Test // B > a + c
    void cannotCreateTriangleWithSideB() {
        try {
            new  Triangle (4.0, 8.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // ok
        }
    }

    @Test // C > b + c
    void cannotCreateTriangleWithSideC() {
        try {
            new  Triangle (4.0, 3.0, 8.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // ok
        }
    }


    // Тесты на равенство треугольников

    @Test //треугольники равны
    void testEquality(){
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(5.0, 3.0, 4.0);
        var t3 = new Triangle(4.0, 5.0, 3.0);

        Assertions.assertEquals(t1, t2);
        Assertions.assertEquals(t1, t3);
        Assertions.assertEquals(t2, t3);
    }


    @Test // треугольники не равны
    void testNonEquality(){
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 6.0);
        Assertions.assertNotEquals(t1, t2);
    }

}
