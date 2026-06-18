package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

//    @Test
//    void canRegisterUser1(String username) {
//        var email = String.format("%s@localhost", username);
//        // создать емейл на почтовом сервере (джеймс хелпер)
//        // открыть браузер заполнить форму, отправляем (браузер)
//        // ждём почту (мейл хелпер)
//        // извлекаем ссылку
//        // в браузере проходим по ссылке. завершаем регестрацию (бруазуер)
//        //проверяем что пользователь может залогиниться (HttpSessionHelper)
//    }

    @Test
    void canRegisterUser() {

        // Генерируем уникальное имя пользователя
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";


        // создать емейл на почтовом сервере (джеймс хелпер)
        app.jamesCli().addUser(
                email,
                password);

        // открыть браузер заполнить форму, отправляем (браузер)
        app.session().registration(username, email);

        // ждём почту (мейл хелпер)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(30));
        Assertions.assertFalse(messages.isEmpty(), "No email received");
        System.out.println(messages);

        // извлекаем ссылку
        var url = app.mail().getConfirmationLink(messages);
        System.out.println("Confirmation link: " + url);


        // в браузере проходим по ссылке. завершаем регестрацию (бруазуер)
        app.session().finishRegistration(url, username, password);

        //проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());

    }


    @Test
    void canRegisterUserRest() {

        // Генерируем уникальное имя пользователя
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";
        System.out.println(email);


        // создать емейл на почтовом сервере (джеймс апи хелпер)
        app.jamesApi().addUser(
                email,
                password);

        // создание нового юзера (рест апи)
        app.rest().registration(username, email);

        // ждём почту (мейл хелпер)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(30));
        Assertions.assertFalse(messages.isEmpty(), "No email received");
        System.out.println(messages);

        // извлекаем ссылку
        var url = app.mail().getConfirmationLink(messages);
        System.out.println("Confirmation link: " + url);


        // в браузере проходим по ссылке. завершаем регестрацию (бруазуер)
        app.session().finishRegistration(url, username, password);

        //проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());

    }

}
