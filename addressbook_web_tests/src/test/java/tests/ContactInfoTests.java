package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

//    @Test
//    void testPhonesOnHomePage() {
//        if (app.hbm().getContactCount() == 0){
//            app.hbm().createContact(new ContactData()
//                    .withNames(
//                    "First name Test " + System.currentTimeMillis() % 10000,
//                    "Last name Test")
//                    .withHome(CommonFunctions.randomDigits(10))
//                    .withMobile(CommonFunctions.randomDigits(10))
//                    .withWork(CommonFunctions.randomDigits(10))
//            );
//            app.contacts().openHomePage();
//        }
//
//
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        app.contacts().openHomePage();
//        var phones = app.contacts().getPhones(contact);
//        var expected = Stream.of(contact.home(), contact.mobile(), contact.work())
//                .filter(s -> s != null && ! "".equals(s))
//                .collect(Collectors.joining("\n"));
//        Assertions.assertEquals(expected, phones);
//    }


    // просмотр всех контактов
//    @Test
//    void testPhonesOnHomePage() {
//        if (app.hbm().getContactCount() == 0){
//            app.hbm().createContact(new ContactData()
//                    .withNames(
//                            "First name Test " + System.currentTimeMillis() % 10000,
//                            "Last name Test")
//                    .withHome(CommonFunctions.randomDigits(10))
//                    .withMobile(CommonFunctions.randomDigits(10))
//                    .withWork(CommonFunctions.randomDigits(10))
//            );
//            app.contacts().openHomePage();
//        }
//        var contacts = app.hbm().getContactList();
//        for (var contact : contacts) {
//            app.contacts().openHomePage();
//            var phones = app.contacts().getPhones(contact);
//            var expected = Stream.of(contact.home(), contact.mobile(), contact.work())
//                    .filter(s -> s != null && ! "".equals(s))
//                    .collect(Collectors.joining("\n"));
//            Assertions.assertEquals(expected, phones);
//        }
//    }

    //через MAP
    @Test
    void testPhonesOnHomePage() {
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData()
                    .withNames(
                            "First name Test " + System.currentTimeMillis() % 10000,
                            "Last name Test")
                    .withHome(CommonFunctions.randomDigits(10))
                    .withMobile(CommonFunctions.randomDigits(10))
                    .withWork(CommonFunctions.randomDigits(10))
            );
            app.contacts().openHomePage();
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);

    }


    @Test
    void testEmailsOnHomePage() {
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData()
                    .withNames(
                            "First name Test " + System.currentTimeMillis() % 10000,
                            "Last name Test")
                    .withEmail(CommonFunctions.randomEmail(5))
                    .withEmail2(CommonFunctions.randomEmail(7))
                    .withEmail3(CommonFunctions.randomEmail(10))
            );
            app.contacts().openHomePage();
        }


        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddressOnHomePage() {
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData()
                    .withNames(
                            "First name Test " + System.currentTimeMillis() % 10000,
                            "Last name Test")
                    .withAddress(CommonFunctions.randomAddress())
            );
            app.contacts().openHomePage();
        }


        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        app.contacts().openHomePage();
        var address = app.contacts().getAddress(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testContactInfoOnHomePage() {
        // Создаём контакт со всеми данными, если нет ни одного
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withNames(
                            "First name Test " + System.currentTimeMillis() % 10000,
                            "Last name Test")
                    .withAddress(CommonFunctions.randomAddress())
                    .withHome(CommonFunctions.randomDigits(8))
                    .withMobile(CommonFunctions.randomDigits(10))
                    .withWork(CommonFunctions.randomDigits(10))
                    .withEmail(CommonFunctions.randomEmail(3))
                    .withEmail2(CommonFunctions.randomEmail(5))
                    .withEmail3(CommonFunctions.randomEmail(10))
            );
            app.contacts().openHomePage();
        }

        // Берём первый контакт
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);

        // выбираем случайный контакт
        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);


        // Проверка наличия телефонов
        if (Stream.of(contact.home(), contact.mobile(), contact.work())
                .noneMatch(s -> s != null && !s.isEmpty())) {
            contact = contact.withHome(CommonFunctions.randomDigits(8));
            app.hbm().modifyContact(contact);
        }

        // Проверка наличия email
        if (Stream.of(contact.email(), contact.email2(), contact.email3())
                .noneMatch(s -> s != null && !s.isEmpty())) {
            contact = contact.withEmail(CommonFunctions.randomEmail(5));
            app.hbm().modifyContact(contact);
        }

        // Проверка наличия адреса
        if (contact.address() == null || contact.address().isEmpty()) {
            contact = contact.withAddress(CommonFunctions.randomAddress());
            app.hbm().modifyContact(contact);
        }

        app.contacts().openHomePage();
        app.contacts().refreshPage();

        // Получаем данные с главной страницы
        var phonesFromMain = app.contacts().getPhones(contact);
        var emailsFromMain = app.contacts().getEmails(contact);
        var addressFromMain = app.contacts().getAddress(contact);


        // Открываем форму редактирования
        app.contacts().openEditForm(contact);

        // Данные из формы редактирования
        var phonesFromEdit = app.contacts().getPhonesFromEditForm();
        var emailsFromEdit = app.contacts().getEmailsFromEditForm();
        var addressFromEdit = app.contacts().getAddressFromEditForm();


        // Проверки
        Assertions.assertEquals(phonesFromEdit, phonesFromMain);
        Assertions.assertEquals(emailsFromEdit, emailsFromMain);
        Assertions.assertEquals(addressFromEdit, addressFromMain);

    }

}
