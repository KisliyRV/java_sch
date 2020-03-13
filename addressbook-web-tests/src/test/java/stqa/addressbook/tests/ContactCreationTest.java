package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testAddressBook() throws Exception {
    Contact before = app.contact().all();
    app.contact().gotoAddNewContact();
    File photo = new File("src/test/resources/123.png");
    ContactData contact = new ContactData()
            .withFirstName("Dima").withLastName("Pupkin").withAddress("Test st. 11").withMobilePhone("1112223334444").withEmail("test55@gmail.com").withYear("2001").withBDay("13").withBMonth("March").withGroup("[none]").withPhoto(photo);
    app.contact().fillForm(contact, true);
    app.contact().submit();
    Contact after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
