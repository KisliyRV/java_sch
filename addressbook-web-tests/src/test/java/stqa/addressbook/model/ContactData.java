package stqa.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id  = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String mobilephone;
    private String homephone;
    private String workphone;
    private String allphone;
    private String email;
    private String email2;
    private String email3;
    private String year;
    private String bday;
    private String bmonth;
    private String group;
    private String allAddresses;
    private String allEmailAddresses;

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData getFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData getLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData getAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData getMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData getEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData getEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData getEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData getYear(String year) {
        this.year = year;
        return this;
    }

    public ContactData getBDay(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData getBMonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData getGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData getHomePhone(String homePhone) {
        this.homephone = homePhone;
        return this;
    }

    public ContactData getWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withAllPhones(String allphone) {
        this.allphone = allphone;
        return this;
    }

    public ContactData getAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }

    public ContactData getAllEmailAddresses(String allEmailAddresses) {
        this.allEmailAddresses = allEmailAddresses;
        return this;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName(){
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilephone;
    }

    public String getHomePhone() {
        return homephone;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public String getAllPhone() {
        return allphone;
    }

    public String getAllAddresses() {
        return allAddresses;
    }

    public String getAllEmailAddresses() {
        return allEmailAddresses;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getBDay() {
        return bday;
    }

    public String getBMonth() {
        return bmonth;
    }

    public String getYear() {
        return year;
    }

    public String getGroup() {
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
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
