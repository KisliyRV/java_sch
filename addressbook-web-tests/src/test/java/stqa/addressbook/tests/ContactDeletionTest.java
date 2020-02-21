package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().homeContact();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", "Pupkin", "Test st. 11", "1112223334444", "test55@gmail.com", "2001", "15", "August", "test1"));
        }
        app.getContactHelper().checkContact();
        app.getContactHelper().submitContactDeletion();
    }
}
