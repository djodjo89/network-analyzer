package model.type.numeric;

import model.type.Hexa;

public class HexadecimalNumeric extends AbstractNumericType {
  public HexadecimalNumeric(int value) {
    super(value);
  }

  public String hexEquivalent(int number) {
    String hexValeur = "";
    int i = 0;
    while (hexValeur.length() == 0 && i < Hexa.values().length) {
      Hexa currentHexa = Hexa.values()[i];
      if (i == number) hexValeur = currentHexa.getValue();
      i++;
    }
    if (hexValeur.length() == 0) throw new NumberFormatException();
    return hexValeur;
  }

  private String fromIntToHexa(int number) {
    if (number < 16) return hexEquivalent(number);
    double divisePar16 = number / 16.;
    String[] splittedNumber = String.valueOf(divisePar16).split("\\.");
    int beforeDecimal = Integer.parseInt(splittedNumber[0]);
    int afterDecimal = (int) (Double.parseDouble("0." + splittedNumber[1]) * 16);

    return fromIntToHexa(beforeDecimal) + hexEquivalent(afterDecimal);
  }

  @Override
  public Integer getValue() {
    return super.getValue();
  }

  @Override
  public String toString() {
    return fromIntToHexa(getValue());
  }
}
