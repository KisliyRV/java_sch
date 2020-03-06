package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.AddressData;

import java.util.ArrayList;
import java.util.List;

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

    public void checkContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector("div.msgbox"));
    }

    public void homeContact() {
        click(By.linkText("home"));
    }

    public void editContact(int index) {
        driver.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
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

    public void modify(int index, AddressData contact) {
       editContact(index);
       fillForm(contact, false);
       updateContact();
       homeContact();
    }

    public void delete(int index) {
        checkContact(index);
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

    public List<AddressData> list() {
        List<AddressData> contacts = new ArrayList<AddressData>();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new AddressData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }
}
