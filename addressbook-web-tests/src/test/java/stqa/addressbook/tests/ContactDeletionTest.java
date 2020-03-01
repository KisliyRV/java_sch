package stqa.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().homeContact();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "15", "August", "test1"));
        }
        app.getContactHelper().checkContact(before -1);
        app.getContactHelper().submitContactDeletion();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before -1);
    }
}
