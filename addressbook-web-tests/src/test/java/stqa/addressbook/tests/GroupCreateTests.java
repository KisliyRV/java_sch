package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

    app.getNavigationHelper().gotoGroupPage(); //Переходим на страницу группы
    app.getGroupHelper().initGroupCreation(); //Создаем тестовую группу
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3")); //Заполняем тестовую группу
    app.getGroupHelper().submitGroupCreation(); //применяем изменения
    app.getNavigationHelper().gotoGroupPage();
  }

}
