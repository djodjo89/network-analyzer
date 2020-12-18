package model.type.string;

import model.type.AbstractType;

public abstract class AbstractStringType extends AbstractType<String> {
  public AbstractStringType(String value) {
    super(value);
  }

  public abstract int getNumericValue();

  public String toString() {
    return super.getValue();
  };

  public String getValueWithOffset(int offset, int max) {
    String result = "";
    for (int i = 0; i < offset && i < max; i++) result += "0";
    return result + super.getValue();
  }
}
