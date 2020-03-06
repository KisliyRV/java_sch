package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;
import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().list().size() == 0) {
            app.contact().createContact(new AddressData("Dima", null, null, null, null, null, null, null, "[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<AddressData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<AddressData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
