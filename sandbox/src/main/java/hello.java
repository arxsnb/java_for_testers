import java.io.File;

public class hello {
    public static void main(String[] args) {

        var x = 1;
        var y = 1;
        if (y == 0) {
            System.out.println("Деление на ноль запрещено");
        } else {
            var z = divide(x, y);
            System.out.println(z);
            System.out.println("Hello, world!");
        }



        System.out.println(2 + 2);
        System.out.println(2 * 2);
        System.out.println(2 - 2);
        System.out.println(2 / 2);
        System.out.println((2 + 2) * 2);

        System.out.println("Hello, " + "world!");
        System.out.println("2 + 2 = " + (2 + 2));


        var configFile = new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsoluteFile());
        System.out.println(configFile.exists());
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}
