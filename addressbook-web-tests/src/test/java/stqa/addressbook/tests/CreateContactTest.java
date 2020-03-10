package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    Contact before = app.contact().all();
    app.contact().gotoAddNewContact();
    ContactData contact = new ContactData()
            .getFirstName("Dima").getLastName("Pupkin").getAddress("Test st. 11").getMobilePhone("1112223334444").getEmail("test55@gmail.com").getYear("2001").getBDay("13").getBMonth("March").getGroup("[none]");
    app.contact().fillForm(contact, true);
    app.contact().submit();
    Contact after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
