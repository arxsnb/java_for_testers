package ru.stqa.geometry.figures;

public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void printTriangleArea(Triangle s) {
        String text = String.format("Площадь Треугольника со сторонами %f, %f  и %f = %f", s.a, s.b, s.c, s.area());
        System.out.println(text);
    }

    public static void printTrianglePerimeter(Triangle s) {
        String text = String.format("Периметр Треугольника со сторонами %f, %f  и %f = %f", s.a, s.b, s.c, s.perimeter());
        System.out.println(text);
    }

    public double area() {
        double p = (this.a + this.b + this.c) / 2;
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }
}
