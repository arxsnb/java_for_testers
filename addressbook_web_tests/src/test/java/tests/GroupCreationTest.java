package tests;

import model.GroupData;
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
        app.groups().openGroupsPage();
        app.groups().createGroup(new GroupData("GR name", "GR header", "GR footer"));
    }


    @Test
    public void CanCreateGroupWithEmptyName() {
        app.groups().openGroupsPage();
        app.groups().createGroup(new GroupData());
    }


    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().openGroupsPage();
        app.groups().createGroup(new GroupData().withName("some name"));
    }
}
