package stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import stqa.addressbook.appmanager.ContactHelper;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContact() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.xml"));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
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
