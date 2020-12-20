package model.network.trame;

import model.network.INetworkObject;
import model.network.entete.IEntete;

import java.util.List;

public abstract class ITrame implements INetworkObject {
    abstract int getNo();
    abstract double getTemps();
    abstract String getSource();
    abstract String getDestination();
    abstract String getProtocoleAbreviation();
    abstract String getInfo();
    public abstract List<IEntete> getEntetes();
    abstract String getHexa();
    abstract int getLength();
    public abstract boolean equals(Object o);
    public abstract String toString();

    public abstract List<? extends INetworkObject> getChildren();
    public abstract String getValue();
    public abstract boolean isRoot();
}
