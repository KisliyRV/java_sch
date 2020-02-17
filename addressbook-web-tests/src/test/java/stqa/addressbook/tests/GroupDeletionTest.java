package stqa.addressbook.tests;

import org.testng.annotations.*;


public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHellper().selectGroup();
    app.getGroupHellper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupPage();
  }

}
