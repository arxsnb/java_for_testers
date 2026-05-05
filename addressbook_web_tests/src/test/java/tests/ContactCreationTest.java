package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void CanCreateContact() {
        app.contacts().createContact(new ContactData(
                "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                "Last name Test", "Nickname Test"
        ));
    }

    @Test
    public void CanCreateContactWithOnlyNames() {
        app.contacts().createContact(new ContactData().withNames(
                "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                "Last name Test"
        ));
    }

}
