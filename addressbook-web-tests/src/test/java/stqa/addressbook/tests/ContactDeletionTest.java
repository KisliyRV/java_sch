package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().getFirstName("Dima").getGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contact before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contact after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
