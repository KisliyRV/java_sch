package stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import stqa.addressbook.appmanager.ContactHelper;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

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
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().group().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactCreation(){
    Groups group = app.db().group();
      File photo = new File("src/test/resources/123.png");
      ContactData newContact = new  ContactData().withFirstName("Test").withLastName("Dom").withPhoto(photo)
              .inGroup(group.iterator().next());
   // Contact before = app.db().contact();
    app.contact().gotoAddNewContact();
    app.contact().fillForm(newContact, true);
    app.contact().submit();
   // Contact after = app.db().contact();
    }
}
