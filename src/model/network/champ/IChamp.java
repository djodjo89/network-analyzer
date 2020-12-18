package model.network.champ;

public interface IChamp<T> {
    String getEtiquette();
    T getValeur();
    String toString();
    int getHauteur();
    boolean isRoot();
    void setNotRoot();
    boolean equals(Object o);
}
