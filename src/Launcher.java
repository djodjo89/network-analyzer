import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {
        Controleur controleur = new Controleur(stage);
        controleur.launch();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
