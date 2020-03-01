package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;

import java.util.List;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage(); //Переходим на страницу группы
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation(); //Создаем тестовую группу
    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, "test3", null)); //Заполняем тестовую группу
    app.getGroupHelper().submitGroupCreation(); //применяем изменения
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() +1);
  }

}
