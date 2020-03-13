package stqa.addressbook.model;

import java.io.File;
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
    private File photo;

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withYear(String year) {
        this.year = year;
        return this;
    }

    public ContactData withBDay(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBMonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homephone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withAllPhones(String allphone) {
        this.allphone = allphone;
        return this;
    }

    public ContactData withAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }

    public ContactData withAllEmailAddresses(String allEmailAddresses) {
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
