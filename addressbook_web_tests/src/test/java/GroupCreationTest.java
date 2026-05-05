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
        openGroupsPage();
        createGroup(new GroupData("GR name", "GR header", "GR footer"));
    }


    @Test
    public void CanCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData());
    }


    @Test
    public void CanCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
