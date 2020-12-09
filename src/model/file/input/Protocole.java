package model.file.input;

public enum Protocole {
    IPV4("Internet Protocol Version 4", "4");

    private String name;
    private String value;

    Protocole(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
