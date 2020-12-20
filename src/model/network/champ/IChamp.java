package model.network.champ;

import model.network.INetworkObject;

import java.util.List;

public abstract class IChamp<T> implements INetworkObject {
    abstract String getEtiquette();
    abstract T getValeur();
    public abstract String toString();
    abstract void setNotRoot();
    public abstract boolean equals(Object o);

    public abstract boolean isRoot();
    public abstract String getValue();
    public abstract List<? extends INetworkObject> getChildren();
}
