package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().homeContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillNewContact(new AddressData("Tolik", "Pult", "ot st. 33", "555000", "test101@gmail.com", "1989", "4", "February"));
        app.getContactHelper().updateContact();
        app.getContactHelper().homeContact();
    }
}
