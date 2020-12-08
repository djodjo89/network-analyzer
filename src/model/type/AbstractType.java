package model.type;

public abstract class AbstractType {
    private int value;

    public AbstractType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract String toString();
}
