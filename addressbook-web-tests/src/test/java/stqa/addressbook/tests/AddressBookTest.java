package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.AddressData;
import java.util.Set;

public class AddressBookTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    Set<AddressData> before = app.contact().all();
    app.contact().gotoAddNewContact();
    AddressData contact = new AddressData()
            .withFirstName("Dima").withLastName("Pupkin").withAddress("Test st. 11").withMobilePhone("1112223334444").withEmail("test55@gmail.com").withYear("2001").withBDay("13").withBMonth("March").withGroup("[none]");
    app.contact().fillForm(contact, true);
    app.contact().submit();
    Set<AddressData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
