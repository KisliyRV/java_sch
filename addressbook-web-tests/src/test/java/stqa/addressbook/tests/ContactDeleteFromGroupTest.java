package stqa.addressbook.tests;

import com.google.common.collect.Sets;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().group().size() == 0) {
            Random random = new Random();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test" + random.nextInt(100)));
        }
        if (app.db().contact().size() == 0) {
            app.goTo().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima").withLastName("Tost"));
            ContactData contactsData = app.db().contact().iterator().next();
            app.contact().addContactToGroup(contactsData);
        }
        List<ContactData> contactList = app.contact().getContactList();
        int counter = 0;
        for (ContactData contact : contactList) {
            if (contact.getGroups().size() > 0) {
                counter++;
                break;
            }
        }
        if (counter == 0) {
        }
        ContactData contactData = app.db().contact().iterator().next();
        app.contact().addContactToGroup(contactData);
    }

    @Test
    public void removeContactFromGroup() {
        app.goTo().homeContact();
        List<ContactData> beforeContactList = app.contact().getContactList();
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

            List<ContactData> afterContactList = app.contact().getContactList();
            Set<GroupData> beforeContactGroups = Sets.difference(contactData.getGroups(), after.getGroups());
            Assert.assertEquals(contactData.getGroups().size() - 1, after.getGroups().size());

            assertThat(after.getGroups(),
                    equalTo(contactData.getGroups().without(beforeContactGroups.iterator().next())));
        }

    }

}
