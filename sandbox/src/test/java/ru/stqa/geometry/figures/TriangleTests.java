package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    //Тесты на площадь

    @Test //Тест на площадь равносторонний
    void canCalculateArea() {
        double result = Triangle.area(5.0, 5.0, 5.0);
        Assertions.assertEquals(10.83, result, 0.01);
    }

    @Test //Тест на площадь прямоугольный
    void canCalculateArea2() {
        double result = Triangle.area(3.0, 4.0, 5.0);
        Assertions.assertEquals(6.0, result);
    }



    //Тесты на периметр

    @Test //Периметр равностороннего треугольника
    void canCalculatePerimeter1() {
        double result = Triangle.perimeter(5.0, 5.0, 5.0);
        Assertions.assertEquals(15.00, result);
    }

    @Test //Периметр прямоугольного треугольника
    void canCalculatePerimeter2() {
        double result = Triangle.perimeter(3.0, 4.0, 5.0);
        Assertions.assertEquals(12.00, result);
    }
}
