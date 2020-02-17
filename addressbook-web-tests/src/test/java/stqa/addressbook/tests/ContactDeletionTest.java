package stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().homeContact();
        app.getContactHelper().checkContact();
        app.getContactHelper().submitContactDeletion();

    }
}
