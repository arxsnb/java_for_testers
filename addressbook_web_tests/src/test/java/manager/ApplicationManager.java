package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;

    public void init() {
        if (driver == null){
            driver = new FirefoxDriver();

            //закрыть браузер после всех тестов
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));

            // Вход в систему
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(995, 811));
            login("admin", "secret");
        }
    }

    private void login(String user, String password) {
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.name("user")).sendKeys(user);
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }

    public  LoginHelper session(){
        if(session == null) {
            session = new LoginHelper();
        }
        return session;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    public void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            // Переход в группы
            driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }
}
