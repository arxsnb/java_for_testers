package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(ApplicationManager manager){
        super(manager);
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("submit"))) {

            click(By.linkText("add new"));
        }
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("Send e-Mail"))) {
             click(By.linkText("home"));
        }
    }


    public void createContact(ContactData contact) {
        openContactPage();
        fillContactForm(contact);
        submitContactCreation();

    }

    public void createContact(ContactData contact, GroupData group) {
        openContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();

    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"),contact.firstname());
        type(By.name("middlename"),contact.middlename());
        type(By.name("lastname"),contact.lastname());
        type(By.name("nickname"),contact.nickname());
        type(By.name("title"),contact.title());
        type(By.name("company"),contact.company());
        type(By.name("address"),contact.address());
//        attach(By.name("photo"), contact.photo());
        if (contact.photo() != null && !contact.photo().isEmpty()) {
            attach(By.name("photo"), contact.photo());
        }
    }


    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void submitContactUpdate() {
        click(By.name("update"));
    }


    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }


    public void AddGroupToContact(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectAddGroup(group);
        addToGroup();
        openHomePage();
    }


    public void RemoveGroupFromContact(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupFilter(group);
        selectContact(contact);
        removeFromGroup();
        openHomePage();
    }


    private void selectGroupFilter(GroupData group) {
        click(By.cssSelector(String.format("select[name='group'] option[value='%s']", group.id())));
    }

    private void removeFromGroup() {
        click(By.name("remove"));
    }



    private void returnToHomePage() {
        click(By.linkText("home page"));
    }


    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void selectAddGroup(GroupData group) {
        click(By.cssSelector(String.format("select[name='to_group'] option[value='%s']", group.id())));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    private void addToGroup() {
        click(By.name("add"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();

    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
        removeSelectedContacts();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact){
        openHomePage();
        selectContactToEdit(contact);
        fillContactForm(modifiedContact);
        submitContactUpdate();
        returnToHomePage();
    }

    private void selectContactToEdit(ContactData contact) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", contact.id())));
    }

    private void selectContactmodify(ContactData contact) {

    }


    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");

            var firstname = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastname = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();


            contacts.add(new ContactData()
                    .withId(id)
                    .withNames(firstname, lastname)
                    );

        }
        return contacts;
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//tr[.//input[@id='%s']]/td[6]", contact.id()))).getText();

    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//tr[.//input[@id='%s']]/td[5]", contact.id()))).getText();
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//tr[.//input[@id='%s']]/td[4]", contact.id()))).getText();
    }
}
