package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
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
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("GR name", "GR header", "GR footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }


    @Test
    public void CanCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }


    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

    @Test
    public void CanCreateMultipleGroups() {
        int n = 5;
        int groupCount = app.groups().getCount();

        for (int i = 0; i < n; i++){
            app.groups().createGroup(new GroupData(randomString(i * 10), "GR header", "GR footer"));
        }

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + n, newGroupCount);
    }
}
