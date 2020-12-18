package model.type.numeric;

public class BinaireNumeric extends AbstractNumericType {
  public BinaireNumeric(int value) {
    super(value);
  }

  @Override
  public String toString() {
    return Integer.toBinaryString(getValue());
  }
}
