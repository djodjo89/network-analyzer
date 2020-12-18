package model.type.string;

public class BinaireString extends AbstractStringType {
  public BinaireString(String value) {
    super(value);
  }

  @Override
  public int getNumericValue() {
    int[] numeric = new int[getValue().length()];
    int sum = 0;
    for(int i = 0; i < numeric.length; i++) {
      int currentValue = Integer.parseInt(String.valueOf(getValue().charAt(i)));
      if (currentValue != 0 && currentValue != 1) throw new NumberFormatException();
      sum += Integer.parseInt(String.valueOf(getValue().charAt(i))) * Math.pow(2, numeric.length - i - 1);
    }
    return sum;
  }

  // 0. 0. 0. 0 0 0 0 0  0 0 0 0 0 0 0 0

  /*public String firstBits(int nbBits) {
    if (nbBits > )
    return super.toString();
  }*/
}
