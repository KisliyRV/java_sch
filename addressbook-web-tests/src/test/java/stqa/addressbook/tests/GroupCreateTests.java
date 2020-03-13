package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreateTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new GroupData().withName("Test1").withHeader("header 1").withFooter("footer1")});
    list.add(new Object[]{new GroupData().withName("Test1").withHeader("header 1").withFooter("footer1")});
    list.add(new Object[]{new GroupData().withName("Test1").withHeader("header 1").withFooter("footer1")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreate(GroupData group) {
    app.goTo().groupPage(); //Переходим на страницу группы
    Groups before = app.group().all();
    app.group().creation(); //Создаем тестовую группу
    app.group().fillForm(group); //Заполняем тестовую группу
    app.group().submit(); //применяем изменения
    app.goTo().groupPage();
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testFallGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before ));
  }
}
