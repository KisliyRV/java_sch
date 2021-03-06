package stqa.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_in_groups")

public class ContactAndGroupData extends ContactData {

    @Id
    @Column(name = "id")
    private int contactId = Integer.MAX_VALUE;

    @Id
    @Column(name = "group_id")
    private int groupId = Integer.MAX_VALUE;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
