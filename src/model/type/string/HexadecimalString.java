package model.type.string;

import model.type.Hexa;

public class HexadecimalString extends AbstractStringType {
  public HexadecimalString(String value) {
    super(value);
  }

  public int numberEquivalent(String number) {
    int hexValeur = -1;
    int i = 0;
    while (hexValeur == -1 && i < Hexa.values().length) {
      Hexa currentHexa = Hexa.values()[i];
      if (currentHexa.getValue().equals(number.toUpperCase())) hexValeur = currentHexa.ordinal();
      i++;
    }
    if (hexValeur == -1) throw new NumberFormatException();
    return hexValeur;
  }

  @Override
  public int getNumericValue() {
    int[] numeric = new int[getValue().length()];
    int sum = 0;
    for(int i = 0; i < numeric.length; i++)
      sum += numberEquivalent(String.valueOf(getValue().charAt(i))) * Math.pow(16, numeric.length - i - 1);
    return sum;
  }
}
