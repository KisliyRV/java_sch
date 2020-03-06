package stqa.addressbook.model;

import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;

public class Contact extends ForwardingSet<AddressData> {

    private Set<AddressData> delegate;

    public Contact(Contact contacts) {
        this.delegate = new HashSet<AddressData>(contacts.delegate);
    }

    public Contact() {
        this.delegate = new HashSet<AddressData>();
    }

    @Override
    protected Set<AddressData> delegate() {
        return delegate;
    }

    public Contact withAdded(AddressData contact){
        Contact contacts = new Contact(this);
        contacts.add(contact);
        return contacts;
    }

    public Contact without(AddressData contact){
        Contact contacts = new Contact(this);
        contacts.remove(contact);
        return contacts;
    }
}