package model.network;

import java.util.List;

public class NetworkObject {
    private NetworkObject parent;
    private final List<NetworkObject> children;
    private final String value;

    public NetworkObject(String value, List<NetworkObject> children, NetworkObject parent) {
        this.value = value;
        this.children = children;
        this.parent = parent;
        children.forEach(object -> object.parent = this);
    }

    public NetworkObject(String value, List<NetworkObject> children) {
        this(value, children, null);
    }

    public NetworkObject(String value) {
        this(value, null);
    }

    public List<NetworkObject> getChildren() {
        return children;
    }

    public String getValue() {
        return value;
    }

    public boolean isRoot() {
        return parent == null;
    }
}
