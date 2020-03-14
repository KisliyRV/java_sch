package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;
import stqa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContact() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.csv"));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2]).withMobilePhone(split[3]).withEmail(split[4]).withGroup(split[5]).withPhoto(new File("src/test/resources/123.png"))});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validContact")
  public void testAddressBook(ContactData contact) throws Exception {
    Contact before = app.contact().all();
    app.contact().gotoAddNewContact();
    app.contact().fillForm(contact, true);
    app.contact().submit();
    Contact after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
