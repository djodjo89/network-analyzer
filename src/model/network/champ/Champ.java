package model.network.champ;

import java.util.Objects;

public class Champ<T> extends AbstractChamp<T> {
    private final String etiquette;
    private final T valeur;

    public Champ(String etiquette, T valeur) {
        this.etiquette = etiquette;
        this.valeur = valeur;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public T getValeur() {
        return valeur;
    }

    public String toString() {
        return etiquette + ": " + valeur;
    }

    public int getHauteur() {
        return 1;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Champ<?> champ = (Champ<?>) o;
    return getEtiquette().equals(champ.getEtiquette()) && getValeur().equals(champ.getValeur());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEtiquette(), getValeur());
  }
}
