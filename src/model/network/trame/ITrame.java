package model.network.trame;

import model.network.INetworkObject;
import model.network.entete.IEntete;

import java.util.List;

public abstract class ITrame implements INetworkObject {
    public abstract int getNo();
    public abstract String getSource();
    public abstract String getDestination();
    public abstract String getProtocoleAbreviation();
    public abstract String getInfo();
    public abstract List<IEntete> getEntetes();
    public abstract String getHexa();
    public abstract int getLength();
    public abstract boolean equals(Object o);
    public abstract String toString();

    public abstract List<? extends INetworkObject> getChildren();
    public abstract boolean isRoot();
    public abstract String getValeur();
}
