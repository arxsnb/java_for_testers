package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTest extends TestBase {

    @Test
    public void CanRemoveContact() {

        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                    "Last name Test"));
        }

        app.contacts().removeContact();
    }

}
