package tests;

import model.ContactData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTest extends TestBase {

    @AfterEach
    void cleanUpDatabase() {
        // Очищаем битые записи после каждого теста
        app.jdbc().cleanBrokenEntries();
    }

//    @Test
//    public void CanRemoveContact() {
//
//        if (app.contacts().getCount() == 0){
//            app.contacts().createContact(new ContactData().withNames(
//                    "First name Test " + System.currentTimeMillis() % 10000,
//                    "Last name Test"));
//        }
//        var oldContacts = app.contacts().getList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldContacts.size());
//        app.contacts().removeContact(oldContacts.get(index));
//        var newContacts = app.contacts().getList();
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.remove(index);
//
//        Assertions.assertEquals(newContacts, expectedList);
//    }


//    @Test
//    public void CanRemoveAllContactsAtOnce() {
//        if (app.contacts().getCount() == 0){
//            app.contacts().createContact(new ContactData().withNames(
//                    "First name Test " + System.currentTimeMillis() % 10000,
//                    "Last name Test"));
//        }
//        app.contacts().removeAllContacts();
//        Assertions.assertEquals(0, app.contacts().getCount());
//    }

    @Test
    public void CanRemoveContact() {

        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000,
                    "Last name Test"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void CanRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000,
                    "Last name Test"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }
}
