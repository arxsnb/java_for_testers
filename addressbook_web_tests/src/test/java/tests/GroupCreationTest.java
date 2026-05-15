package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GroupCreationTest extends TestBase {

    public static List<String> groupNameProvider() {
        var result = new ArrayList<String>(List.of("group name", "group name'"));
        for (int i = 0; i < 5; i++){
            result.add(randomString(i * 10));
        }
        return result;
    }


    /*
    @AfterEach
    public void tearDown() {
        //Выход
        //driver.findElement(By.linkText("Logout")).click();
        //driver.quit();
    }
    */

//    @Test
//    public void CanCreateGroup() {
//        int groupCount = app.groups().getCount();
//        app.groups().createGroup(new GroupData("GR name", "GR header", "GR footer"));
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount + 1, newGroupCount);
//    }

//    @ParameterizedTest
//    @ValueSource(strings = {"group name", "group name'"})
//    public void CanCreateGroup(String name) {
//        int groupCount = app.groups().getCount();
//        app.groups().createGroup(new GroupData(name, "GR header", "GR footer"));
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount + 1, newGroupCount);
//    }


    @Test
    public void CanCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }


    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

//    @Test
//    public void CanCreateMultipleGroups() {
//        int n = 5;
//        int groupCount = app.groups().getCount();
//
//        for (int i = 0; i < n; i++){
//            app.groups().createGroup(new GroupData(randomString(i * 10), "GR header", "GR footer"));
//        }
//
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount + n, newGroupCount);
//    }

    @ParameterizedTest
    @MethodSource("groupNameProvider")
    public void CanCreateMultipleGroups(String name) {

        int groupCount = app.groups().getCount();


            app.groups().createGroup(new GroupData(name, "GR header", "GR footer"));


        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }
}
