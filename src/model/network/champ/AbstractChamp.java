package model.network.champ;

import model.network.INetworkObject;

import java.util.List;

public abstract class AbstractChamp<T> extends IChamp<T> {
    private boolean isRoot = true;

    public boolean isRoot() {
        return isRoot;
    }

    public void setNotRoot() {
        isRoot = false;
    }

    public abstract String getEtiquette();
    public abstract T getValeur();
    public String toString() {
        return getEtiquette() + " : " + getValeur();
    }
    public abstract List<? extends INetworkObject> getChildren();
    public abstract String getValue();
}
