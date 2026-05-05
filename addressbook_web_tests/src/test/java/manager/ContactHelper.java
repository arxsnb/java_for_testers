package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

public class ContactHelper {
    private final ApplicationManager manager;

    public ContactHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            // Переход в группы
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

    public void createContact(ContactData contact) {
        openContactPage();

        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys(contact.nickname());
        manager.driver.findElement(By.name("submit")).click();

    }
}
