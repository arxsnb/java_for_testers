package ru.stqa.geometry.figures;

public record Rectangle(double a, double b) {

//    private double a;
//    private  double b;
//
//    public Rectangle(double a, double b){
//        this.a = a;
//        this.b = b;
//    }

    public Rectangle {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Сторона не может быть меньше нуля");
        }
    }

    public static void printRectangleArea(double a, double b) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
