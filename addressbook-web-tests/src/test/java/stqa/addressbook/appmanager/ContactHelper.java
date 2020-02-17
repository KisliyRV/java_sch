package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import stqa.addressbook.model.AddressData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitNewContact() {
        click(By.linkText("home"));
   //   driver.findElement(By.linkText("home")).click();
    }

    public void fillNewContact(AddressData addressData) {
      driver.findElement(By.name("firstname")).click();
      driver.findElement(By.name("firstname")).clear();
      driver.findElement(By.name("firstname")).sendKeys(addressData.getFirstname());
      driver.findElement(By.name("lastname")).clear();
      driver.findElement(By.name("lastname")).sendKeys(addressData.getLastname());
      driver.findElement(By.name("address")).click();
      driver.findElement(By.name("address")).clear();
      driver.findElement(By.name("address")).sendKeys(addressData.getAddress());
      driver.findElement(By.name("mobile")).click();
      driver.findElement(By.name("mobile")).clear();
      driver.findElement(By.name("mobile")).sendKeys(addressData.getMobilephone());
      driver.findElement(By.name("email")).click();
      driver.findElement(By.name("email")).clear();
      driver.findElement(By.name("email")).sendKeys(addressData.getEmail());
      driver.findElement(By.name("bday")).click();
      new Select(driver.findElement(By.name("bday"))).selectByVisibleText(addressData.getBday());
      driver.findElement(By.name("bmonth")).click();
      new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText(addressData.getBmonth());
      driver.findElement(By.name("byear")).click();
      driver.findElement(By.name("byear")).clear();
      driver.findElement(By.name("byear")).sendKeys(addressData.getYear());
      driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void gotoAddNewContact() {
        driver.findElement(By.linkText("add new")).click();
    }
}
