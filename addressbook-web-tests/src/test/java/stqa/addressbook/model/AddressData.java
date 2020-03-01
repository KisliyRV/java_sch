package stqa.addressbook.model;

import java.util.Objects;

public class AddressData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobilephone;
    private final String email;
    private final String year;
    private final String bday;
    private final String bmonth;
    private String group;

    public AddressData(String firstname, String lastname, String address, String mobilephone, String email, String year, String bday, String bmonth, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobilephone = mobilephone;
        this.email = email;
        this.year = year;
        this.bday = bday;
        this.bmonth = bmonth;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public String getYear() {
        return year;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressData that = (AddressData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactsData{" +
                "firstname='" + firstname + '\'' +
                '}';
    }

}
