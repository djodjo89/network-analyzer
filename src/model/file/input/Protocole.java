package model.file.input;

public enum Protocole {
    ICMP("Internet Control Message Protocol", 1),
    UDP("User Datagram Protocol", 17),
    TCP("Transmission Control Protocol", 6);

    private String name;
    private int value;

    Protocole(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getAbreviation() {
        return name();
    }

    public int getValue() {
        return value;
    }
}
