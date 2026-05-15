package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"),contact.firstname());
        type(By.name("middlename"),contact.middlename());
        type(By.name("lastname"),contact.lastname());
        type(By.name("nickname"),contact.nickname());
        type(By.name("title"),contact.title());
        type(By.name("company"),contact.company());
        type(By.name("address"),contact.address());
    }


    private void submitContactCreation() {
        click(By.name("submit"));
    }


    public void removeContact() {
        openHomePage();
        selectContact();
        removeSelectedContacts();
        returnToHomePage();
    }



    private void returnToHomePage() {
        click(By.linkText("home page"));
    }


    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
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
}
