package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima").withGroup("[none]"));
        }
    }

    @Test
    public void testContactPhones() {
        app.contact().homeContact();
        ContactData contact = app.db().contact().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditFrom)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n" ));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
