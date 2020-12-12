package model.type;

public enum Hexa {
    ZERO("0"),
    UN("1"),
    DEUX("2"),
    TROIS("3"),
    QUATRE("4"),
    CINQ("5"),
    SIX("6"),
    SEPT("7"),
    HUIT("8"),
    NEUF("9"),
    DIX("A"),
    ONZE("B"),
    DOUZE("C"),
    TREIZE("D"),
    QUATORZE("E"),
    QUINZE("F");

    private String value;

    Hexa(String value) {
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }
}
