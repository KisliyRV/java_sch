package stqa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contact extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contact(Contact contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contact() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contact(Collection<ContactData> contact) {
        this.delegate = new HashSet<ContactData>(contact);
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contact withAdded(ContactData contact){
        Contact contacts = new Contact(this);
        contacts.add(contact);
        return contacts;
    }

    public Contact without(ContactData contact){
        Contact contacts = new Contact(this);
        contacts.remove(contact);
        return contacts;
    }
}