package model.network.champ;

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
}
