import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase {


    /*
    @AfterEach
    public void tearDown() {
        //Выход
        //driver.findElement(By.linkText("Logout")).click();
        //driver.quit();
    }
    */

    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup("GR name", "GR header", "GR footer");

    }


    @Test
    public void CanCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");

    }
}
