package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();

        // Фиксированные комбинации (4 штуки)
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                result.add(new ContactData().withNames(firstname, lastname));
            }
        }

        // Случайные контакты (5 штук)
        for (int i = 0; i < 5; i++) {
            String randomStr = randomString(i * 10);
            result.add(new ContactData().withNames(randomStr, randomStr));
        }

        return result;
    }

//    @Test
//    public void CanCreateContact() {
//        int contactCount = app.contacts().getCount();
//        app.contacts().createContact(new ContactData(
//                "", "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
//                "Last name Test", "Nickname Test", "Title name Test",
//                "Company Test", "Address Test"
//        ));
//        int newContactCount = app.contacts().getCount();
//        Assertions.assertEquals(contactCount + 1, newContactCount);
//    }

    @Test
    public void CanCreateContact() {
        int contactCount = app.contacts().getCount();
//        app.contacts().createContact(new ContactData(
//                "", "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
//                "Last name Test", "Nickname Test", "Title name Test",
//                "Company Test", "Address Test"
//        ));
        var contact = new ContactData()
                .withNames(randomString(10), randomString(10))
//                .withPhoto("src/test/resources/images/avatar.png");
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void CanCreateContactWithOnlyNames() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData().withNames(
                "First name Test " + System.currentTimeMillis() % 10000,
                "Last name Test"
        ));
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }


//    @ParameterizedTest
//    @MethodSource("contactProvider")
//    public void CanCreateMultipleContact(ContactData contact) {
//        int contactCount = app.contacts().getCount();
//        app.contacts().createContact(contact);
//        int newContactCount = app.contacts().getCount();
//        Assertions.assertEquals(contactCount + 1, newContactCount);
//    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();

        Comparator<ContactData> compareById = (o1, o2) -> {
          return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
