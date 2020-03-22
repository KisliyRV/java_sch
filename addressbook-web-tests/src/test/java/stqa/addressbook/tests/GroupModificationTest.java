package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().group().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().group();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test5").withHeader( "test4").withFooter( "test3");
        app.goTo().groupPage();
        app.group().modify(group);
        app.goTo().groupPage();
        // app.switchTo().alert().accept(); //У меня больше окно не выскакивает.
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().group();
        assertThat(after, equalTo(
                before.without(modifiedGroup).withAdded(group)));
        verifyGroupListUI();
    }
}
