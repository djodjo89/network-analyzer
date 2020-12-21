package model.network.champ;

import model.network.INetworkObject;

import java.util.List;

public abstract class AbstractChamp extends IChamp {
    private boolean isRoot = true;

    public boolean isRoot() {
        return isRoot;
    }

    public void setNotRoot() {
        isRoot = false;
    }

    public abstract String getEtiquette();

    public abstract String getValeur();
    public abstract String toString();
    public abstract List<? extends INetworkObject> getChildren();
}
