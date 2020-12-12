package model.type;

public class Hexadecimal extends AbstractType {
  public Hexadecimal(int value) {
    super(value);
  }

  private String fromIntToHexa(int number) {
    if (number < 16) {
      String rawValue = String.valueOf(number);
      String hexValeur = "";
      int i = 0;
      while (hexValeur.length() == 0 && i < Hexa.values().length) {
        Hexa currentHexa = Hexa.values()[i];
        if (currentHexa.getValue().equals(rawValue.toUpperCase())) hexValeur = currentHexa.getValue();
        i++;
      }
      return hexValeur;
    }
    int divisePar16 = number / 16;
    int remainder = number % 16;

  }

  @Override
  public String toString() {
    String res = "";

    return null;
  }
}
