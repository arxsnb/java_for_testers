import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupDeleteTestTestTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null){
            driver = new FirefoxDriver();

            //закрыть браузер после всех тестов
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));

            // Вход в систему
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(995, 811));
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }

    }

    /*
    @AfterEach
    public void tearDown() {
        //Выход
        //driver.findElement(By.linkText("Logout")).click();
        //driver.quit();
    }
    */

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    @Test
    public void CanRemoveGroup() {

        if (! isElementPresent(By.name("new"))) {
            // Переход в группы
            driver.findElement(By.linkText("groups")).click();
        }

        if (!isElementPresent(By.name("selected[]"))){
            driver.findElement(By.name("new")).click();
            driver.findElement(By.id("content")).click();
            driver.findElement(By.name("group_name")).click();
            driver.findElement(By.name("group_name")).sendKeys("GR name");
            driver.findElement(By.name("group_header")).click();
            driver.findElement(By.name("group_header")).sendKeys("GR header");
            driver.findElement(By.name("group_footer")).click();
            driver.findElement(By.name("group_footer")).sendKeys("GR footer");
            driver.findElement(By.name("submit")).click();
            driver.findElement(By.linkText("group page")).click();
        }

        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();


        driver.findElement(By.linkText("group page")).click();

        //Выход
        driver.findElement(By.linkText("Logout")).click();
    }
}
