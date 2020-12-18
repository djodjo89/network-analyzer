package model.type.numeric;

public class DecimalNumeric extends AbstractNumericType {
  public DecimalNumeric(int value) {
    super(value);
  }

  @Override
  public String toString() {
    return String.valueOf(getValue());
  }
}
