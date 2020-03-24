package stqa.addressbook.appmanager;

import com.google.common.collect.Sets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contact;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static stqa.addressbook.tests.TestBase.app;

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
     // select(By.name("bday"), contactData.getBDay());
     // select(By.name("bmonth"), contactData.getBMonth());
     // type(By.name("byear"), contactData.getYear());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
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

    public void clickGroup() {
        driver.findElement(By.name("to_group")).click();
    }

    public void selectGroupFilterByGroupId(int groupId) {
        new Select(driver.findElement(By.name("group"))).selectByValue(String.valueOf(groupId));
    }

    public void selectGroup(ContactData contactData, boolean selection) {

        int contactGroupSize = contactData.getGroups().size();
        int totalDBGroupSize = app.db().group().size();
        if (selection) {
            if (contactGroupSize == 0 || contactGroupSize == totalDBGroupSize) {
                Random random = new Random();
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test" + random.nextInt(10)));
                app.goTo().homeContact();
                checkContactById(contactData.getId());

                Groups totalGroup = app.db().group();
                Groups totalContactGroup =  contactData.getGroups();
                Set<GroupData> contactNotInGroup = Sets.difference(totalGroup, totalContactGroup);
                new Select(driver.findElement(By.name("to_group"))).selectByValue(String.valueOf(contactNotInGroup.iterator().next().getId()));
            } else {
                Groups totalGroup = app.db().group();
                Groups totalContactGroups =  contactData.getGroups();
                Set<GroupData> contactNotInGroup = Sets.difference(totalGroup, totalContactGroups);
                new Select(driver.findElement(By.name("to_group"))).selectByValue(String.valueOf(contactNotInGroup.iterator().next().getId()));
            }

        }
    }


    public void addGroup() {
        driver.findElement(By.name("add")).click();
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
    public void removeGroup(ContactData contact) {
        checkContactById(contact.getId());
        clickDeleteContactFromGroup();
        homeContact();
    }

    public void clickDeleteContactFromGroup() {
        driver.findElement(By.name("remove")).click();
    }

    public void fillForm(ContactData contactData) {
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void addContactToGroup(ContactData contact) {
        checkContactById(contact.getId());
        clickGroup();
        selectGroup(contact, true);
        addGroup();
        homeContact();
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

    public List<ContactData> getContactList() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.close();
        return result;
    }

    public List<ContactData> getGroupList() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<ContactData> result = session.createQuery("from GroupData").list();
        session.close();
        return result;
    }
}
