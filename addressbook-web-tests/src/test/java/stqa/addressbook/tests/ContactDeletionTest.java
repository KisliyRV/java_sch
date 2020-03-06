package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;
import java.util.Set;

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
        Set<AddressData> before = app.contact().all();
        AddressData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<AddressData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
