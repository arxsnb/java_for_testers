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
}
