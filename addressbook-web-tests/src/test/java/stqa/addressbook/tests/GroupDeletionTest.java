package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().group().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().group();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    app.goTo().groupPage();
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().group();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}
