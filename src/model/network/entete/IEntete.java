package model.network.entete;

import model.network.INetworkObject;
import model.network.champ.IChamp;
import model.network.trame.ITrame;

import java.util.List;

public abstract class IEntete implements INetworkObject {
    abstract String getProtocole();
    abstract String getDescription();
    public abstract List<IChamp> getChamps();
    public abstract boolean equals(Object o);
    public abstract String toString();

    public abstract List<? extends INetworkObject> getChildren();
    public abstract String getValue();
    public abstract boolean isRoot();
}
