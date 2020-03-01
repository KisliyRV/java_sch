package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().homeContact();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "13", "March", null));
        }
        List<AddressData> before = app.getContactHelper().getContactList();
        app.getContactHelper().checkContact(before.size() -1);
        app.getContactHelper().submitContactDeletion();
        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);
    }
}
