package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.AddressData;
import stqa.addressbook.model.Contact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submit() {
        click(By.name("submit"));
        click(By.linkText("home"));
    }

    public void fillForm(AddressData addressData, boolean creation) {
      type(By.name("firstname"),addressData.withFirstName());
      type(By.name("lastname"), addressData.withLastName());
      type(By.name("address"), addressData.withAddress());
      type(By.name("mobile"), addressData.withMobilePhone());
      type(By.name("email"), addressData.withEmail());
      select(By.name("bday"), addressData.withBDay());
      select(By.name("bmonth"), addressData.withBMonth());
      type(By.name("byear"), addressData.withYear());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(addressData.withGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }


    public void checkContactById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector("div.msgbox"));
    }

    public void homeContact() {
        click(By.linkText("home"));
    }

    public void editContactById(int id) {
        driver.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
        // click(By.xpath("(//img[@alt='Edit'])"));
    }

    public void updateContact() {
        click(By.name("update"));
    }
    public void createContact(AddressData addressData) {
        gotoAddNewContact();
        fillForm(addressData);
        submit();

    }

    public void modify(AddressData contact) {
       editContactById(contact.getId());
       fillForm(contact, false);
       updateContact();
       homeContact();
    }

    public void delete(AddressData contact) {
        checkContactById(contact.getId());
        submitContactDeletion();
    }

    public void fillForm(AddressData addressData) {
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    //public int getContactCount() {
     //   return driver.findElements(By.name("selected[]")).size();
    //}

    public Contact all() {
        Contact contacts = new Contact();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allAddresses = cells.get(3).getText();
            String allEmailAddresses = cells.get(4).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new AddressData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones).withAddress(allAddresses).withAllEmailAddresses(allEmailAddresses));
        }
        return contacts;
    }

    public AddressData infoFromEdinFrom(AddressData contact) {
        editContactById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String address = driver.findElement(By.cssSelector("textarea[name=\"address\"]")).getText();
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");

        driver.navigate().back();
        return new AddressData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
