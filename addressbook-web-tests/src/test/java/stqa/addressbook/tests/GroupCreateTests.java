package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage(); //Переходим на страницу группы
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().initGroupCreation(); //Создаем тестовую группу
    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, "test3")); //Заполняем тестовую группу
    app.getGroupHelper().submitGroupCreation(); //применяем изменения
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before +1);
  }

}
