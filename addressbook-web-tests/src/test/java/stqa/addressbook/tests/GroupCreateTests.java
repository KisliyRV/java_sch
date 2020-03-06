package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;
import java.util.Set;


public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {
    app.goTo().groupPage(); //Переходим на страницу группы
    Set<GroupData> before = app.group().all();
    app.group().creation(); //Создаем тестовую группу
    GroupData group = new GroupData().withName("test1");
    app.group().fillForm(group); //Заполняем тестовую группу
    app.group().submit(); //применяем изменения
    app.goTo().groupPage();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() +1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
