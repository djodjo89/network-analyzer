package model.type;

public abstract class AbstractType<T> {
  private T value;
  public AbstractType(T value) {
    this.value = value;
  }
  public T getValue() {
    return value;
  }
  public abstract int getNumericValue();
  public abstract String toString();
}
