package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submit() {
        click(By.name("submit"));
        click(By.linkText("home"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      attach(By.name("photo"), contactData.getPhoto());
      type(By.name("address"), contactData.getAddress());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("email"), contactData.getEmail());
      //select(By.name("bday"), contactData.getBDay());
      //select(By.name("bmonth"), contactData.getBMonth());
      //type(By.name("byear"), contactData.getYear());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
    public void createContact(ContactData contactData) {
        gotoAddNewContact();
        fillForm(contactData);
        submit();

    }

    public void modify(ContactData contact) {
       editContactById(contact.getId());
       fillForm(contact, false);
       updateContact();
       homeContact();
    }

    public void delete(ContactData contact) {
        checkContactById(contact.getId());
        submitContactDeletion();
    }

    public void fillForm(ContactData contactData) {
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
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones).withAddress(allAddresses).withAllEmailAddresses(allEmailAddresses));
        }
        return contacts;
    }

    public ContactData infoFromEditFrom(ContactData contact) {
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
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
