import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Controleur controleur = new Controleur(stage);

        // Afficher les trames
        // Les sauvegarder dans un fichier
        System.out.println("Hello world!");
    }

    public static void main(String args[]) {
        launch(args);
    }
}
