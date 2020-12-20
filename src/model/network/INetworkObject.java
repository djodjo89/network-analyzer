package model.network;

import java.util.List;

public interface INetworkObject  {
    List<? extends INetworkObject> getChildren();
    String getValue();
    boolean isRoot();
}
