package vue.trame;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import model.network.INetworkObject;
import model.network.trame.ITrame;

public class VueNetworkObject extends TreeItem<INetworkObject> implements VueTrame {
    public VueNetworkObject(INetworkObject object) {
        super(object);
    }

    @Override
    public ObservableList<TreeItem<INetworkObject>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;

            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            INetworkObject o = getValue();
            isLeaf = !o.isRoot();
        }

        return isLeaf;
    }

    private ObservableList<TreeItem<INetworkObject>> buildChildren(TreeItem<INetworkObject> TreeItem) {
        INetworkObject c = TreeItem.getValue();
        if (c != null && c.isRoot()) {
            System.out.println(c.getValue());
            System.out.println(c.getChildren());
            List<INetworkObject> objects = (List<INetworkObject>) c.getChildren();
            if (objects != null) {
                ObservableList<TreeItem<INetworkObject>> children = FXCollections
                        .observableArrayList();

                for (INetworkObject childChamp : objects) {
                    children.add(new VueNetworkObject(childChamp));
                }

                return children;
            }
        }

        return FXCollections.emptyObservableList();
    }

    @Override
    public ITrame getTrame() {
        return null;
    }

    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;
    private boolean isLeaf;
}