package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().getFirstName("Dima").getGroup("[none]"));
        }
    }

    @Test
    public void testContactAddresses() {
        app.contact().homeContact();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
    }

    private String mergeAddresses(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n" ));
    }

}