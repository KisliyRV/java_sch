package stqa.addressbook.model;

public class AddressData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobilephone;
    private final String email;
    private final String year;
    private final String bday;
    private final String bmonth;

    public AddressData(String firstname, String lastname, String address, String mobilephone, String email, String year, String bday, String bmonth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobilephone = mobilephone;
        this.email = email;
        this.year = year;
        this.bday = bday;
        this.bmonth = bmonth;
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
}
