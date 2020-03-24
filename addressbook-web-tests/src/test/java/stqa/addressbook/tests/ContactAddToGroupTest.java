package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
        List<ContactData> beforeContact = app.contact().getContactList();
        List<ContactData> beforeGroup = app.contact().getGroupList();
        ContactData contactData = app.db().contact().iterator().next();
        app.contact().addContactToGroup(contactData);
        List<ContactData> afterContact = app.contact().getContactList();
        List<ContactData> afterGroup = app.contact().getGroupList();
        ContactData after = null;
        Iterator<ContactData> allContacts = app.db().contact().iterator();

        while (allContacts.hasNext()) {
            after = allContacts.next();
            if (after.getId() == contactData.getId()) {
               break;
            }
        }
        app.goTo().homeContact();
        Assert.assertEquals(contactData.getGroups().size(), after.getGroups().size());


    }
}
