package model.network.champ;

import model.network.INetworkObject;

import java.util.List;

public abstract class IChamp implements INetworkObject {
    public abstract String getEtiquette();
    public abstract String toString();
    abstract void setNotRoot();
    public abstract boolean equals(Object o);

    public abstract String getValeur();
    public abstract boolean isRoot();
    public abstract List<? extends INetworkObject> getChildren();
}
