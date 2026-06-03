package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

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
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
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
        var address = app.contacts().getAddress(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, address);
    }

}
