package model.type.string;

public class DecimalString extends AbstractStringType {
  public DecimalString(String value) {
    super(value);
  }

  @Override
  public int getNumericValue() {
    return Integer.parseInt(String.valueOf(getValue()));
  }
}
