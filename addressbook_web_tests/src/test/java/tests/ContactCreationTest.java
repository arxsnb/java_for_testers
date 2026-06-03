package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTest extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();

        // Фиксированные комбинации (4 штуки)
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                result.add(new ContactData().withNames(firstname, lastname));
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);

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
                .withNames(CommonFunctions.randomString(10), CommonFunctions.randomString(10))
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

//    @ParameterizedTest
//    @MethodSource("contactProvider")
//    public void CanCreateMultipleContact(ContactData contact) {
//        var oldContacts = app.contacts().getList();
//        app.contacts().createContact(contact);
//        var newContacts = app.contacts().getList();
//
//        Comparator<ContactData> compareById = (o1, o2) -> {
//          return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newContacts.sort(compareById);
//
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newContacts, expectedList);
//    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId)
                .withHome("")
                .withMobile("")
                .withWork(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    public void CanCreateContactInGroup() {

        var contact = new ContactData()
                .withNames(CommonFunctions.randomString(10), CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "GR name", "GR header", "GR footer"));
            app.groups().refreshPage();
        }
        var group = app.hbm().getGroupList().get(0);


        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var maxId = newRelated.get(newRelated.size() - 1).id();

        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);


        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        Assertions.assertEquals(newRelated, expectedList);
    }



    @Test
    public void CanAddGroupToExistingContact() {

        //Проверяем наличие контактов. если нет, то создаём
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000,
                    "Last name Test"));
        }


        // Проверяем наличие групп. создаём если нет
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "GR name", "GR header", "GR footer"));
        }


        // Выбор случайной группы
        var groupList = app.hbm().getGroupList();
        var rnd = new Random();
        var indexGroup = rnd.nextInt(groupList.size());
        var selectedGroup = groupList.get(indexGroup);

        // Получаем список ВСЕХ контактов
        var allContacts = app.hbm().getContactList();

        // Получаем список контактов, которые уже в группе
        var oldContactsInGroup = app.hbm().getContactsInGroup(selectedGroup);

        // Вычетаем из всех те что в группе
        var availableContacts = new ArrayList<ContactData>();
        for (var contact : allContacts) {
            boolean alreadyInGroup = false;
            for (var groupContact : oldContactsInGroup) {
                if (groupContact.id().equals(contact.id())) {
                    alreadyInGroup = true;
                    break;
                }
            }
            if (!alreadyInGroup) {
                availableContacts.add(contact);
            }
        }


        // Выбираем контакт (создаём если нет доступных)
        ContactData selectedContact;
        if (availableContacts.isEmpty()) {
            var newContact = new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000,
                    "Last name Test " + System.currentTimeMillis() % 10000);
            app.hbm().createContact(newContact);

            // Получаем список контактов после создания
            var allContactsAfter = app.hbm().getContactList();

            // Сортируем по ID
            Comparator<ContactData> compareById = (o1, o2) ->
                    Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            allContactsAfter.sort(compareById);

            // Берём последний контакт (с максимальным ID)
            selectedContact = allContactsAfter.get(allContactsAfter.size() - 1);
        } else {
            var indexContact = rnd.nextInt(availableContacts.size());
            selectedContact = availableContacts.get(indexContact);
        }

        //Добавление группы в контакт
        app.contacts().AddGroupToContact(selectedContact, selectedGroup);


        var newContactsInGroup = app.hbm().getContactsInGroup(selectedGroup);

        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContactsInGroup.sort(compareById);

        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.add(selectedContact);
        expectedList.sort(compareById);


        Assertions.assertEquals(newContactsInGroup, expectedList);
    }





    @Test
    public void CanDeletedGroupToExistingContact() {

        //Проверяем наличие контактов. если нет, то создаём
        if (app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData().withNames(
                    "First name Test " + System.currentTimeMillis() % 10000,
                    "Last name Test"));
        }


        // Проверяем наличие групп. создаём если нет
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "GR name " + System.currentTimeMillis() % 10000, "GR header", "GR footer"));
        }


        var rnd = new Random();
        //Выбор случайной группы
        var groupList = app.hbm().getGroupList();
        var indexGroup = rnd.nextInt(groupList.size());
        var selectedGroup = groupList.get(indexGroup);


        //Получаем список контактов в группе ДО добавления
        var oldContactsInGroup = app.hbm().getContactsInGroup(selectedGroup);


        // Если в группе нет контактов — создаём и добавляем
        if (oldContactsInGroup.isEmpty()) {
            if (app.hbm().getContactCount() == 0){
                app.hbm().createContact(new ContactData().withNames(
                        "First name Test " + System.currentTimeMillis() % 10000,
                        "Last name Test"));
            }

            //Выбор случайного контакта
            var contactList = app.hbm().getContactList();
            var indexContact = rnd.nextInt(contactList.size());
            var selectedContact = contactList.get(indexContact);

            // Добавляем контакт в группу
            app.contacts().AddGroupToContact(selectedContact, selectedGroup);

            // Обновляем список контактов в группе
            oldContactsInGroup = app.hbm().getContactsInGroup(selectedGroup);
        }

        // выбираем случайный контакт из группы
        var selectedContact = oldContactsInGroup.get(rnd.nextInt(oldContactsInGroup.size()));


        app.contacts().RemoveGroupFromContact(selectedContact, selectedGroup);



        var newContactsInGroup = app.hbm().getContactsInGroup(selectedGroup);


        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContactsInGroup.sort(compareById);

        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.remove(selectedContact);
        expectedList.sort(compareById);


        Assertions.assertEquals(newContactsInGroup, expectedList);


    }

}
