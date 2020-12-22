package vue;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.network.trame.ITrame;


public class VueTramesTable extends TableView<ITrame> {
    private ITrame current;
    private ObservableList<ITrame> trames;
    private Pane container;

    public VueTramesTable(ITrame current, ObservableList<ITrame> trames, Pane container) {
        super();
        this.current = current;
        this.trames = trames;
        this.container = container;
        initialize();
    }

    private void initialize() {
        TableColumn<ITrame, String> noCol = new TableColumn<>("No.");
        TableColumn<ITrame, String> sourceCol = new TableColumn<>("Source");
        TableColumn<ITrame, String> destCol = new TableColumn<>("Destination");
        TableColumn<ITrame, String> protoCol = new TableColumn<>("Protocol");
        TableColumn<ITrame, String> lengthCol = new TableColumn<>("Length");
        TableColumn<ITrame, String> infoCol = new TableColumn<>("Info");

        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        protoCol.setCellValueFactory(new PropertyValueFactory<>("protocoleAbreviation"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        infoCol.setCellValueFactory(new PropertyValueFactory<>("info"));

        this.setItems(trames);

        this
                .getColumns()
                .addAll(noCol, sourceCol, destCol, protoCol, lengthCol, infoCol);

        this
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection)
                                -> {
                            if (newSelection != null) {
                                current = newSelection;
                                container.getChildren().remove(container.getChildren().size() - 1);
                                container.getChildren().add(new TreeView<>(new VueTrameHexa(current)));
                            }
                        });
    }
}
