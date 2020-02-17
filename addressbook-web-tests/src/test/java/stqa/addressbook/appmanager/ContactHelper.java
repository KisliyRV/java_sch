package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stqa.addressbook.model.AddressData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitNewContact() {
        click(By.linkText("home"));
    }

    public void fillNewContact(AddressData addressData) {
      type(By.name("firstname"),addressData.getFirstname());
      type(By.name("lastname"), addressData.getLastname());
      type(By.name("address"), addressData.getAddress());
      type(By.name("mobile"), addressData.getMobilephone());
      type(By.name("email"), addressData.getEmail());
      select(By.name("bday"), addressData.getBday());
      select(By.name("bmonth"), addressData.getBmonth());
      type(By.name("byear"), addressData.getYear());
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void checkContact() {
        click(By.id("1"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void homeContact() {
        click(By.linkText("home"));
    }

    public void editContact() {
        click(By.xpath("(//img[@alt='Edit'])[2]"));
    }

    public void updateContact() {
        click(By.name("update"));
    }
}
