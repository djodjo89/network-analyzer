import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.network.trame.ITrame;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        List<ITrame> trames;
        // Recuperer les donnees du fichier et les convertir sous forme de trames
        stage.setTitle("Network analyzer");
        stage.setResizable(false);

        VBox vbox = new VBox();
        Canvas canvas = new Canvas(1024,768);
        vbox.getChildren().addAll(canvas);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        // Afficher les trames
        // Les sauvegarder dans un fichier
        System.out.println("Hello world!");
    }

    public static void main(String args[]) {
        launch(args);
    }
}
