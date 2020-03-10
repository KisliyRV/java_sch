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
            app.contact().createContact(new ContactData().getFirstName("Dima").getGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Contact before = app.contact().all();
        ContactData modyfiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modyfiedContact.getId()).getFirstName("Tolik").getLastName("Pult").getAddress("ot st. 33").getMobilePhone("555000").getEmail("test101@gmail.com").getYear("1989").getBDay("18").getBMonth("July");
        app.contact().modify(contact);
        Contact after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modyfiedContact).withAdded(contact)));
    }
}
