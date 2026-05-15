package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTest extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var middlename : List.of("", "middlename")) {
                for (var lastname : List.of("", "lastname")) {
                    result.add(new ContactData().withNames(firstname, middlename, lastname));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData().withNames(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }

        return result;
    }

    @Test
    public void CanCreateContact() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData(
                "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                "Last name Test", "Nickname Test", "Title name Test",
                "Company Test", "Address Test"
        ));
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void CanCreateContactWithOnlyNames() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData().withNames(
                "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                "Last name Test"
        ));
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

}
