package stqa.addressbook;

import org.testng.annotations.*;

public class AddressBookTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    gotoAddNewContact();
    fillNewContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "15", "August"));
    submitNewContact();
    returnLogaut(); //выходим из системы
  }

}
