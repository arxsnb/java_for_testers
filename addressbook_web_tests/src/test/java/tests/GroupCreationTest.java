package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

//    public static List<String> groupNameProvider() {
//        var result = new ArrayList<String>(List.of("group name", "group name'"));
//        for (int i = 0; i < 5; i++){
//            result.add(randomString(i * 10));
//        }
//        return result;
//    }

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "", "")));
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


//    @Test
//    public void CanCreateGroupWithEmptyName() {
//        app.groups().createGroup(new GroupData());
//    }


//    @Test
//    public void CanCreateGroupWithNameOnly() {
//        app.groups().createGroup(new GroupData().withName("some name"));
//    }

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

//    @ParameterizedTest
//    @MethodSource("groupNameProvider")
//    public void CanCreateMultipleGroups(String name) {
//
//        int groupCount = app.groups().getCount();
//
//
//            app.groups().createGroup(new GroupData(name, "GR header", "GR footer"));
//
//
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount + 1, newGroupCount);
//    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void CanCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void CanNotCreateGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
