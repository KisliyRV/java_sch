package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().homeContact();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "15", "August", "test1"));
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillNewContact(new AddressData("Tolik", "Pult", "ot st. 33", "555000", "test101@gmail.com", "1989", "4", "February", null), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().homeContact();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
