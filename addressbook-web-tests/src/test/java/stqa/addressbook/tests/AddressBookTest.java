package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.AddressData;

import java.util.List;

public class AddressBookTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    List<AddressData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillNewContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "13", "March", "[none]"), true);
    app.getContactHelper().submitNewContact();
    List<AddressData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);
  }
}
