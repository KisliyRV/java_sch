package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getContactHelper().homeContact();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new AddressData("Dima", null, null, null, null, null, null, null, "[none]"));
        }
    }

    @Test
    public void testContactModification() {
        List<AddressData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        AddressData contact = new AddressData (before.get(index).getId(),"Tolik", "Pult", "ot st. 33", "555000", "test101@gmail.com", "1989", "18", "July", null);
        app.getContactHelper().madifyContact(index, contact);
        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super AddressData> byId = Comparator.comparingInt(AddressData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
