package stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static stqa.addressbook.tests.TestBase.app;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex) {
            jCommander.usage();
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contact = generateContacts(count);
        if (format.equals("csv")) {
           saveCsv(contact, new File(file));
         } else if(format.equals("xml")){
              saveXml(contact, new File(file));
          } else {
            System.out.println("Неизвестный формат" + format);
         }
    }

    private static void saveCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            Groups groups = app.db().group();
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getMobilePhone(), contact.getEmail(), contact.getPhoto()));
        }
        writer.close();
    }

    private void saveXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
                    .withLastName(String.format("LastName %s", i)).withAddress(String.format("Address %s", i)).withMobilePhone(String.format("mobilephone %s", i)).withEmail(String.format("emailaddress %s", i))
                    .withPhoto(new File("src/test/resources/123.png")));
        }
        return contacts;
    }
}
