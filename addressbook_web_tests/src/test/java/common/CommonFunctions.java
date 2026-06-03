package common;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//public class CommonFunctions {
//    public static String randomString(int n) {
//        var rnd = new Random();
//        var result = "";
//        for (int i = 0; i < n; i++){
//            result = result + (char)('a' + rnd.nextInt(26));
//        }
////        if (n < 20) {
////            result = result + '\'';
////        }
//        return result;
//    }

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());

        return result;
    }

    public static String randomDigits(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(10);  // 0-9
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(String::valueOf)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomEmail(int n) {
        var rnd = new Random();
        String domain = "@test.com";

        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);  // 0-25 для букв
        var localPart = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());

        return localPart + domain;
    }


    public static String randomAddress() {
        var rnd = new Random();
        var streets = List.of("Ленина", "Советская", "Мира", "Пушкина", "Гагарина");
        var cities = List.of("Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань");

        var streetNum = rnd.nextInt(100) + 1;
        var street = streets.get(rnd.nextInt(streets.size()));
        var city = cities.get(rnd.nextInt(cities.size()));

        return String.format("%s\nул. %s, д. %d",
                city, street, streetNum);
    }

}
