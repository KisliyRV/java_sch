package stqa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class ContactAndGroup extends ForwardingSet<ContactAndGroupData> {

    private Set<ContactAndGroupData> delegate;

    public ContactAndGroup(ContactAndGroup contactAndGroup) {
        this.delegate = new HashSet<ContactAndGroupData>(contactAndGroup.delegate);

    }

    @Override
    protected Set<ContactAndGroupData> delegate() {
        return delegate;
    }

    public ContactAndGroup withAdded(ContactAndGroupData contactAndGroup) {
        ContactAndGroup contactsInGroups = new ContactAndGroup(this);
        contactsInGroups.add(contactAndGroup);
        return contactsInGroups;
    }

    public ContactAndGroup without(ContactAndGroupData contactAndGroup) {
        ContactAndGroup contactsInGroups = new ContactAndGroup(this);
        contactsInGroups.remove(contactAndGroup);
        return contactsInGroups;
    }
}
