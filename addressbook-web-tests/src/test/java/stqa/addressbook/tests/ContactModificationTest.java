package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().homeContact();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", null, null, null, null, null, null, null, "[none]"));
        }
        List<AddressData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        AddressData contact = new AddressData (before.get(before.size()-1).getId(),"Tolik", "Pult", "ot st. 33", "555000", "test101@gmail.com", "1989", "18", "July", null);
        app.getContactHelper().fillNewContact(contact, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().homeContact();
        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super AddressData> byId = Comparator.comparingInt(AddressData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
