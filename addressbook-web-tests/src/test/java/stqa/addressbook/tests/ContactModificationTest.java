package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Contact before = app.db().contact();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/123.png");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Tolik").withLastName("Pult").withPhoto(photo).withAddress("test")
                .withMobilePhone("555000").withEmail("test101@gmail.com").withYear("2001").withBDay("13").withBMonth("March").withGroup("[none]");
        app.contact().modify(contact);
        Contact after = app.db().contact();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListUI();
    }
}
