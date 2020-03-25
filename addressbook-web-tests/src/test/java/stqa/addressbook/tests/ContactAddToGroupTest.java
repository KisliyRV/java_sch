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

public class ContactAddToGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima").withLastName("Pypkin"));
        }
        if (app.db().group().size() == 0) {
            Random random = new Random();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test" + random.nextInt(10)));
        }
    }

    @Test
    public void addContactToGroup() {
        app.goTo().homeContact();
        List<ContactData> beforeContactList = app.contact().getContactList();
        ContactData contactData = null;

        int counter = 0;

        int totalDBGroupSize = app.db().group().size();
        for (ContactData contact : beforeContactList) {
            if (contact.getGroups().size() != totalDBGroupSize) {
                contactData = contact;
                counter++;
                break;
            }
        }

        if (counter == 0) {
            app.goTo().homeContact();
            app.contact().createContact(new ContactData().withFirstName("Dima2").withLastName("Pypkin3"));
            beforeContactList = app.contact().getContactList();
            for (ContactData contact : beforeContactList) {
                if (contact.getGroups().size() != totalDBGroupSize) {
                    contactData = contact;
                    break;
                }
            }
        }



        app.contact().addContactToGroup(contactData);
        List<ContactData> afterContact = app.contact().getContactList();

        ContactData after = null;
        Iterator<ContactData> allContacts = app.db().contact().iterator();

        while (allContacts.hasNext()) {
            after = allContacts.next();
            if (after.getId() == contactData.getId()) {
               break;
            }
        }
        ContactData bContact = null;
        ContactData aContact = null;

        if (afterContact.size() > beforeContactList.size()) {
            afterContact.removeAll(beforeContactList);
            contactData = afterContact.get(0);
        }
        for (ContactData  contactbefore : beforeContactList) {
            if (contactbefore.getId() == contactData.getId()) {
                bContact = contactbefore;
            }
        }
        for (ContactData contactafter : afterContact) {
            if (contactafter.getId() == contactData.getId()) {
                aContact = contactafter;
            }
        }
        Set<GroupData> beforeContactGroups = Sets.difference(aContact.getGroups(), bContact.getGroups());

        Assert.assertEquals(contactData.getGroups().size() + 1, after.getGroups().size());
        assertThat(aContact.getGroups(),
                equalTo(bContact.getGroups().withAdded(beforeContactGroups.iterator().next())));

        System.out.println("==============================");
        System.out.println("BEFORE: " + bContact);
        System.out.println(bContact.getGroups());
        System.out.println("AFTER: " + aContact);
        System.out.println(aContact.getGroups());
        System.out.println("==============================");
    }
}
