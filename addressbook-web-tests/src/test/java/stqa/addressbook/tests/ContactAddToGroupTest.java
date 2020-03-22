package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.Contact;
import stqa.addressbook.model.ContactData;

import java.util.Iterator;
import java.util.List;

public class ContactAddToGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima"));
        }
    }

    @Test
    public void addContactToGroup() {
        app.goTo().homeContact();
        ContactData contactData = app.db().contact().iterator().next();
        app.contact().addContactToGroup(contactData);
        app.goTo().homeContact();
        ContactData after = null;
        Iterator<ContactData> allContacts = app.db().contact().iterator();

        while (allContacts.hasNext()) {
            after = allContacts.next();
            if (after.getId() == contactData.getId()) {
               break;
            }
        }

        Assert.assertEquals(contactData.getGroups().size() + 1, after.getGroups().size());

    }
}
