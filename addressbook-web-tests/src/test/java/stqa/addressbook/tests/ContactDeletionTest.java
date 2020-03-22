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
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contact before = app.db().contact();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contact after = app.db().contact();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListUI();
    }
}
