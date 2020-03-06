package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.AddressData;
import java.util.Comparator;
import java.util.List;

public class AddressBookTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    app.contact().gotoAddNewContact();
    List<AddressData> before = app.contact().list();
    AddressData contact = new AddressData()
            .withFirstName("Dima").withLastName("Pupkin").withAddress("Test st. 11").withMobilePhone("1112223334444").withEmail("test55@gmail.com").withYear("2001").withBDay("13").withBMonth("March").withGroup("[none]");
    app.contact().fillForm(contact, true);
    app.contact().submit();
    List<AddressData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super AddressData> byId = Comparator.comparingInt(AddressData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
