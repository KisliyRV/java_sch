package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getContactHelper().homeContact();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", null, null, null, null, null, null, null, "[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<AddressData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deleteContact(index);
        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
