package tests;

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

        app.openGroupsPage();

        if (!app.isGroupPresent()){
            app.createGroup(new GroupData("GR name", "GR header", "GR footer"));
        }

        app.removeGroup();
    }

}
