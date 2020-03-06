package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;
import java.util.Set;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new AddressData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Set<AddressData> before = app.contact().all();
        AddressData modyfiedContact = before.iterator().next();
        AddressData contact = new AddressData()
                .withId(modyfiedContact.getId()).withFirstName("Tolik").withLastName("Pult").withAddress("ot st. 33").withMobilePhone("555000").withEmail("test101@gmail.com").withYear("1989").withBDay("18").withBMonth("July");
        app.contact().modify(contact);
        Set<AddressData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modyfiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
