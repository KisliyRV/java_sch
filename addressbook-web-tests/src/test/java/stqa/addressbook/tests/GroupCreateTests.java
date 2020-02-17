package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

    app.gotoGroupPage(); //Переходим на страницу группы
    app.initGroupCreation(); //Создаем тестовую группу
    app.fillGroupForm(new GroupData("test1", "test2", "test3")); //Заполняем тестовую группу
    app.submitGroupCreation(); //применяем изменения
    app.gotoGroupPage();
    app.returnLogaut(); //выходим из системы
  }

}
