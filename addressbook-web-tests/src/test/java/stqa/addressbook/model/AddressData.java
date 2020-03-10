package stqa.addressbook.model;

import java.util.Objects;

public class AddressData {
    private int id  = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String mobilephone;
    private String email;
    private String year;
    private String bday;
    private String bmonth;
    private String group;

    public int getId() {
        return id;
    }

    public AddressData withId(int id) {
        this.id = id;
        return this;
    }

    public AddressData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public AddressData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public AddressData withAddress(String address) {
        this.address = address;
        return this;
    }

    public AddressData withMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public AddressData withEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressData withYear(String year) {
        this.year = year;
        return this;
    }

    public AddressData withBDay(String bday) {
        this.bday = bday;
        return this;
    }

    public AddressData withBMonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public AddressData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String withFirstName() {
        return firstname;
    }

    public String withLastName(){
        return lastname;
    }

    public String withAddress() {
        return address;
    }

    public String withMobilePhone() {
        return mobilephone;
    }

    public String withEmail() {
        return email;
    }

    public String withBDay() {
        return bday;
    }

    public String withBMonth() {
        return bmonth;
    }

    public String withYear() {
        return year;
    }

    public String withGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactsData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressData that = (AddressData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
