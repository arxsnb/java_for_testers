package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // создать емейл на почтовом сервере (джеймс хелпер)
        // открыть браузер заполнить форму, отправляем (браузер)
        // ждём почту (мейл хелпер)
        // извлекаем ссылку
        // в браузере проходим по ссылке. завершаем регестрацию (бруазуер)
        //проверяем что пользователь может залогиниться (HttpSessionHelper)
    }
}
