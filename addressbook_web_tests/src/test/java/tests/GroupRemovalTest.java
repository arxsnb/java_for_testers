package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTest extends TestBase {


    /*
    @AfterEach
    public void tearDown() {
        //Выход
        //driver.findElement(By.linkText("Logout")).click();
        //driver.quit();
    }
    */



//    @Test
//    public void CanRemoveGroup() {
//
//        if (app.groups().getCount() == 0){
//            app.groups().createGroup(new GroupData("", "GR name", "GR header", "GR footer"));
//        }
//        var oldGroups = app.groups().getList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldGroups.size());
//        app.groups().removeGroup(oldGroups.get(index));
//        var newGroups = app.groups().getList();
//        var expectedList = new ArrayList<>(oldGroups);
//        expectedList.remove(index);
//        Assertions.assertEquals(newGroups, expectedList);
//    }


    @Test
    public void CanRemoveGroup() {

        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "GR name", "GR header", "GR footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

//    @Test
//    public void canRemoveAllGroupsAtOnce(){
//        if (app.groups().getCount() == 0){
//            app.groups().createGroup(new GroupData("", "GR name", "GR header", "GR footer"));
//        }
//        app.groups().removeAllGroups();
//        Assertions.assertEquals(0, app.groups().getCount());
//    }

    @Test
    public void canRemoveAllGroupsAtOnce(){
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "GR name", "GR header", "GR footer"));
            app.groups().refreshPage();
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }

}
