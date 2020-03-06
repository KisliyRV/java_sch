package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;
import stqa.addressbook.model.Contact;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new AddressData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contact before = app.contact().all();
        AddressData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contact after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
