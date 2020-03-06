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
        app.contact().homeContact();
        if (app.contact().list().size() == 0) {
            app.contact().createContact(new AddressData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        List<AddressData> before = app.contact().list();
        int index = before.size() - 1;
        AddressData contact = new AddressData()
                .withId(before.get(index).getId()).withFirstName("Tolik").withLastName("Pult").withAddress("ot st. 33").withMobilePhone("555000").withEmail("test101@gmail.com").withYear("1989").withBDay("18").withBMonth("July");
        app.contact().modify(index, contact);
        List<AddressData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super AddressData> byId = Comparator.comparingInt(AddressData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
