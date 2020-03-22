package stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import stqa.addressbook.appmanager.ApplicationManager;
import stqa.addressbook.model.Contact;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method  m, Object[] p){
        logger.info("Start test" + m.getName() + "witg parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun =  true)
    public void logTestStop(Method  m){
        logger.info("Stop test" + m.getName());
    }

    public void verifyGroupListUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().group();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactListUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contact dbContact = app.db().contact();
            Contact uiContact = app.contact().all();
            assertThat(uiContact, equalTo(dbContact.stream()
                    .map((g) -> new ContactData().withId(g.getId()).withFirstName(g.getFirstName()))
                    .collect(Collectors.toSet())));
        }
    }
}