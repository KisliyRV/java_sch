package stqa.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")

@Entity
@Table(name = "addressbook")

public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id  = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;

    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Transient
    private String allphone;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Column(name = "byear")
   // @Type(type = "text")
    private String year;

    @Column(name = "bday", columnDefinition = "TINYINT")
    private String bday;

    @Column(name = "bmonth")
   // @Type(type = "text")
    private String bmonth;

    @Transient
    private String group;

    @Transient
    private String allAddresses;

    @Transient
    private String allEmailAddresses;

    @Column(name = "photo")//, columnDefinition = "LONGVARCHAR")
    @Type(type = "text")
    private String photo;

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public File getPhoto() {
        return new File (photo);
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
