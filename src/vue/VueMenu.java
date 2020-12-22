package vue;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.file.input.FichierExtracteur;
import model.file.input.LigneMalFormatteeException;
import model.file.output.FichierSauvegardeur;
import model.network.trame.ITrame;

import java.io.File;
import java.util.List;

public class VueMenu extends MenuBar {
    private final FileChooser fileChooser;
    private ITrame current;
    private final Stage stage;
    private final ObservableList<ITrame> trames;
    private final FichierExtracteur fichierExtracteur;
    private final FichierSauvegardeur fichierSauvegardeur;
    private final Pane entetesTrameContainer;
    private final Popup errorPopup;

    public VueMenu(ITrame current, ObservableList<ITrame> trames, Stage stage, Pane entetesTrameContainer) {
        this.current = current;
        this.trames = trames;
        this.stage = stage;
        this.entetesTrameContainer = entetesTrameContainer;
        fileChooser = new FileChooser();
        fichierSauvegardeur = new FichierSauvegardeur();
        fichierExtracteur = new FichierExtracteur();
        errorPopup = new Popup();
        initialize();
    }

    private void showErrorPopup(Exception e) {
        Label errorLabel = new Label(e.getMessage());

        Button okButton = new Button("OK");
        okButton.setOnMouseClicked(event -> errorPopup.hide());
        HBox buttonContainer = new HBox(okButton);
        buttonContainer.setPadding(new Insets(10, 10, 10, 10));
        buttonContainer.setAlignment(Pos.CENTER);

        VBox errorContainer = new VBox(errorLabel, buttonContainer);
        errorContainer.setStyle(" -fx-background-color: white;");
        errorContainer.setStyle("-fx-border-color: black");
        errorContainer.setPadding(new Insets(10, 10, 10, 10));
        errorContainer.setMinHeight(50);
        errorContainer.setMinWidth(120);
        errorContainer.setAlignment(Pos.CENTER);
        errorPopup.getContent().add(errorContainer);
        errorPopup.show(stage);
    }

    private void initialize() {
        Menu fileMenu = new Menu("Fichier");
        MenuItem open = new MenuItem("Ouvrir");
        MenuItem save = new MenuItem("Sauvegarder");

        save.setOnAction(e -> {
            fileChooser.setTitle("Sauvegarder un fichier");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                fichierSauvegardeur.sauvegarder(file.getPath(), trames);
            }
        });

        open.setOnAction(e -> {
            fileChooser.setTitle("Ouvrir un fichier");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    List<ITrame> nouvellesTrames = fichierExtracteur.extraireTrames(file.getPath());
                    trames.clear();
                    trames.addAll(nouvellesTrames);
                    current = trames.get(0);

                    entetesTrameContainer.getChildren().remove(entetesTrameContainer.getChildren().size() - 1);
                    entetesTrameContainer.getChildren().add(new TreeView<>(new VueTrameHexa(current)));
                } catch (LigneMalFormatteeException ligneMalFormatteeException) {
                    showErrorPopup(ligneMalFormatteeException);
                }
            }
        });

        fileMenu.getItems().add(open);
        fileMenu.getItems().add(save);
        getMenus().add(fileMenu);
    }
}
