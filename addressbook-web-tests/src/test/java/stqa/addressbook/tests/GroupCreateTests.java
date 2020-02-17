package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

    app.getNavigationHelper().gotoGroupPage(); //Переходим на страницу группы
    app.getGroupHellper().initGroupCreation(); //Создаем тестовую группу
    app.getGroupHellper().fillGroupForm(new GroupData("test1", "test2", "test3")); //Заполняем тестовую группу
    app.getGroupHellper().submitGroupCreation(); //применяем изменения
    app.getNavigationHelper().gotoGroupPage();
  }

}
