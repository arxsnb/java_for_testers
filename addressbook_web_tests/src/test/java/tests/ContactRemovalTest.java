package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTest extends TestBase {

    @Test
    public void CanRemoveContact() {

        if (app.contacts().getCount() == 0){
            app.contacts().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                    "Last name Test"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }


    @Test
    public void CanRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0){
            app.contacts().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000, "Middle name Test",
                    "Last name Test"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
