package stqa.addressbook;

import org.testng.annotations.*;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {

    gotoGroupPage(); //Переходим на страницу группы
    initGroupCreation(); //Создаем тестовую группу
    fillGroupForm(new GroupData("test1", "test2", "test3")); //Заполняем тестовую группу
    submitGroupCreation(); //применяем изменения
    gotoGroupPage();
    returnLogaut(); //выходим из системы
  }

}
