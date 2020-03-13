package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().homeContact();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactEmailAddresses() {
        app.contact().homeContact();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);

        assertThat(contact.getAllEmailAddresses(), equalTo(mergeEmailAddresses(contactInfoFromEditForm)));
    }

    private String mergeEmailAddresses(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailAddressTest::cleaned)
                .collect(Collectors.joining("\n" ));
    }

    public static String cleaned(String emailAddress) {
        return emailAddress.replaceAll("\\s", "").replaceAll("[-()]","");
    }

}
