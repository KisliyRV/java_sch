package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test4", "test3"));
        app.getGroupHelper().subminGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        //driver.findElement(By.cssSelector("div.msgbox"));
        app.switchTo().alert().accept(); //У меня больше окно не выскакивает.
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }

}
