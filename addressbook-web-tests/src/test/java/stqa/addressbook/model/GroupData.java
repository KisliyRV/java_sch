package stqa.addressbook.model;

public class GroupData {
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(String name, String heder, String footer) {
        this.name = name;
        this.header = heder;
        this.footer = footer;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}