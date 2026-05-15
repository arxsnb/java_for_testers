package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {

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

}
