package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.AddressData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitNewContact() {
        click(By.name("submit"));
        click(By.linkText("home"));
    }

    public void fillNewContact(AddressData addressData, boolean creation) {
      type(By.name("firstname"),addressData.getFirstname());
      type(By.name("lastname"), addressData.getLastname());
      type(By.name("address"), addressData.getAddress());
      type(By.name("mobile"), addressData.getMobilephone());
      type(By.name("email"), addressData.getEmail());
      select(By.name("bday"), addressData.getBday());
      select(By.name("bmonth"), addressData.getBmonth());
      type(By.name("byear"), addressData.getYear());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(addressData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void checkContact() {
        click(By.name("selected[]"));
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
    public void createContact(AddressData addressData) {
        gotoAddNewContact();
        fillNewContact(addressData);
        submitNewContact();

    }

    private void fillNewContact(AddressData addressData) {
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("add new"));
    }
}
