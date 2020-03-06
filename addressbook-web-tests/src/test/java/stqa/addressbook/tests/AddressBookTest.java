package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import stqa.addressbook.model.AddressData;
import stqa.addressbook.model.Contact;

import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressBookTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    Contact before = app.contact().all();
    app.contact().gotoAddNewContact();
    AddressData contact = new AddressData()
            .withFirstName("Dima").withLastName("Pupkin").withAddress("Test st. 11").withMobilePhone("1112223334444").withEmail("test55@gmail.com").withYear("2001").withBDay("13").withBMonth("March").withGroup("[none]");
    app.contact().fillForm(contact, true);
    app.contact().submit();
    Contact after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
