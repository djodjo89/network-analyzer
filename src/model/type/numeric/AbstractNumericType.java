package model.type.numeric;

import model.type.AbstractType;

public abstract class AbstractNumericType extends AbstractType<Integer> {
  public AbstractNumericType(int value) {
    super(value);
  }

  public int getNumericValue() {
    return super.getValue();
  }

  public abstract String toString();
}
