package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.AddressData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase {

    @Test
    public void testContactPhones() {
        app.contact().homeContact();
        AddressData contact = app.contact().all().iterator().next();
        AddressData contactInfoFromEditFrom = app.contact().infoFromEdinFrom(contact);

        assertThat(contact.withAllPhone(), equalTo(mergePhones(contactInfoFromEditFrom)));
    }

    private String mergePhones(AddressData contact) {
        return Arrays.asList(contact.withHomePhone(), contact.withMobilePhone(), contact.withWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n" ));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
