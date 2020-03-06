package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {
    app.goTo().groupPage(); //Переходим на страницу группы
    List<GroupData> before = app.group().list();
    app.group().creation(); //Создаем тестовую группу
    GroupData group = new GroupData().withName("test1");
    app.group().fillForm(group); //Заполняем тестовую группу
    app.group().submit(); //применяем изменения
    app.goTo().groupPage();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
