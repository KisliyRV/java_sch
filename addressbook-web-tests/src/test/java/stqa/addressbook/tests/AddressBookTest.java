package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.AddressData;

public class AddressBookTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillNewContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "15", "August"));
    app.getContactHelper().submitNewContact();
  }

}
