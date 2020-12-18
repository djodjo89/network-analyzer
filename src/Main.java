import controleur.Controleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.file.input.FichierExtracteur;
import model.network.trame.ITrame;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        List<ITrame> trames;
        // Recuperer les donnees du fichier et les convertir sous forme de trames V
        trames = new FichierExtracteur().extraireTrames("test/file/data/trames.txt");
        Controleur controleur = new Controleur(stage, trames);

        // Afficher les trames
        // Les sauvegarder dans un fichier
        System.out.println("Hello world!");
    }

    public static void main(String args[]) {
        launch(args);
    }
}
