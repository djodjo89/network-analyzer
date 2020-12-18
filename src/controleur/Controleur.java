package controleur;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.network.trame.ITrame;

import java.util.List;

public class Controleur {
  private Stage stage;
  private List<ITrame> trames;

  public Controleur(Stage stage, List<ITrame> trames) {
    this.stage = stage;
    this.trames = trames;
    stage.setTitle("Network analyzer");

    VBox vbox = new VBox();
    Canvas canvas = new Canvas(1024, 768);
    vbox.getChildren().addAll(canvas, tramesListHeader());
    Scene scene = new Scene(vbox);
    stage.setScene(scene);
    stage.show();
  }

  private Separator verticalSeparator() {
    return new Separator(Orientation.VERTICAL);
  }

  private HBox tramesListHeader() {
    return new HBox(
            new Label("No."),
            verticalSeparator(),
            new Label("Time"),
            verticalSeparator(),
            new Label("Source"),
            verticalSeparator(),
            new Label("Destination"),
            verticalSeparator(),
            new Label("Protocol"),
            verticalSeparator(),
            new Label("Length"),
            verticalSeparator(),
            new Label("Info"),
            verticalSeparator());
  }

  private List<VBox> trames() {

  }
}
