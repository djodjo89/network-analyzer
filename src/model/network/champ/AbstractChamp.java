package model.network.champ;

public abstract class AbstractChamp<T> implements IChamp<T> {
    private int hMax = 1;
    private boolean isRoot = true;

    public boolean isRoot() {
        return isRoot;
    }

    public void setNotRoot() {
        isRoot = false;
    }

    public abstract String getEtiquette();
    public abstract T getValeur();
    public abstract int getHauteur();
}
