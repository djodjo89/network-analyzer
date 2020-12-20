package model.network.champ;

import model.network.INetworkObject;

import java.util.List;
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

    @Override
    public List<? extends INetworkObject> getChildren() {
        return null;
    }

    @Override
    public String getValue() {
        return valeur.toString();
    }

    @Override
    public boolean isRoot() {
        return false;
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
