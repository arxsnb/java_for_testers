import java.io.File;

public class hello {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

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
}
