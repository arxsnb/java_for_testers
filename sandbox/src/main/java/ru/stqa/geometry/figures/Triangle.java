package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

//    private double a;
//    private double b;
//    private double c;
//
//    public Triangle(double a, double b, double c){
//        this.a = a;
//        this.b = b;
//        this.c = c;
//    }

    public Triangle {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть больше 0");
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                    String.format("Стороны %f, %f, %f не образуют треугольник", a, b, c)
            );
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.a, a) == 0 && Double.compare(triangle.b, b) == 0 && Double.compare(triangle.c, c) == 0)
                || (Double.compare(triangle.a, a) == 0 && Double.compare(triangle.b, c) == 0 && Double.compare(triangle.c, b) == 0)
                || (Double.compare(triangle.a, b) == 0 && Double.compare(triangle.b, a) == 0 && Double.compare(triangle.c, c) == 0)
                || (Double.compare(triangle.a, b) == 0 && Double.compare(triangle.b, c) == 0 && Double.compare(triangle.c, a) == 0)
                || (Double.compare(triangle.a, c) == 0 && Double.compare(triangle.b, a) == 0 && Double.compare(triangle.c, b) == 0)
                || (Double.compare(triangle.a, c) == 0 && Double.compare(triangle.b, b) == 0 && Double.compare(triangle.c, a) == 0)
                ;
    }

    @Override
    public int hashCode() {
        return 1;
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
