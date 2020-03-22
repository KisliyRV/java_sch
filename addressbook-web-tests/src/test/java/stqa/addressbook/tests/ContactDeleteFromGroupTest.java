package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.Iterator;

public class ContactDeleteFromGroupTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contact().size() == 0) {
            app.goTo().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima").withLastName("Tost"));
            ContactData contactsData = app.db().contact().iterator().next();
            app.contact().addContactToGroup(contactsData);
        }
        ContactData contactData = app.db().contact().iterator().next();
        if (contactData.getGroups().size() == 0)
            app.contact().addContactToGroup(contactData);

    }

    @Test
    public void removeContactFromGroup() {
        app.goTo().homeContact();
        ContactData contactData = null;

        Iterator<ContactData> allContacts = app.db().contact().iterator();

        while (allContacts.hasNext()) {
            contactData = allContacts.next();
            if (contactData.getGroups().size() > 0) {
                break;
            }
        }
        if (contactData != null && contactData.getGroups().size() > 0) {
            app.contact().selectGroupFilterByGroupId(contactData.getGroups().iterator().next().getId());
            app.contact().removeGroup(contactData);

            ContactData after = null;

            Iterator<ContactData> updatedContacts = app.db().contact().iterator();

            while (updatedContacts.hasNext()) {
                after = updatedContacts.next();
                if (after.getId() == contactData.getId()) {
                    break;
                }
            }

            Assert.assertEquals(contactData.getGroups().size() - 1, after.getGroups().size());
        }

    }

}
