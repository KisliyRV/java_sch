package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Contact before = app.contact().all();
        ContactData modyfiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modyfiedContact.getId()).withFirstName("Tolik").withLastName("Pult").withAddress("ot st. 33").withMobilePhone("555000").withEmail("test101@gmail.com").withYear("1989").withBDay("18").withBMonth("July");
        app.contact().modify(contact);
        Contact after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modyfiedContact).withAdded(contact)));
    }
}
