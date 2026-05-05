import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTest extends TestBase {


    /*
    @AfterEach
    public void tearDown() {
        //Выход
        //driver.findElement(By.linkText("Logout")).click();
        //driver.quit();
    }
    */



    @Test
    public void CanRemoveGroup() {

        openGroupsPage();

        if (!isGroupPresent()){
            createGroup(new GroupData("GR name", "GR header", "GR footer"));
        }

        removeGroup();

        //Выход
        //driver.findElement(By.linkText("Logout")).click();
    }

}
