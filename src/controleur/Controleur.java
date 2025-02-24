package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.network.trame.ITrame;
import vue.VueMenu;
import vue.VueTramesTable;

public class Controleur {
    private Stage stage;
    private ITrame current;
    private ObservableList<ITrame> trames;
    private VBox screen;

    public Controleur(Stage stage) {
        this.stage = stage;
        this.trames = FXCollections.observableArrayList();
        stage.setTitle("Network analyzer");

        this.screen = new VBox();
        Canvas canvas = new Canvas(1024, 768);
        screen.getChildren().addAll(
                new VueMenu(current, trames, stage, screen),
                new VueTramesTable(current, trames, screen),
                canvas);
    }

    public void launch() {
        Scene scene = new Scene(screen);
        stage.setScene(scene);
        stage.show();
    }
}
