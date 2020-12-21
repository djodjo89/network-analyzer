package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.file.input.FichierExtracteur;
import model.file.input.LigneMalFormatteeException;
import model.file.output.FichierSauvegardeur;
import model.network.trame.ITrame;
import vue.VueTrameHexa;

import java.io.File;
import java.util.List;

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
        screen.getChildren().addAll(menu(), vueTrames(), canvas);
    }

    public void launch() {
        Scene scene = new Scene(screen);
        stage.setScene(scene);
        stage.show();
    }

    private TableView<ITrame> vueTrames() {
        TableView<ITrame> table = new TableView<>();
        TableColumn<ITrame, String> noCol = new TableColumn<>("No.");
        TableColumn<ITrame, String> timeCol = new TableColumn<>("Time");
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

        table.setItems(trames);

        table
                .getColumns()
                .addAll(noCol, sourceCol, destCol, protoCol, lengthCol, infoCol);

        table
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection)
                                -> {
                            if (newSelection != null) {
                                current = newSelection;
                                screen.getChildren().remove(screen.getChildren().size() - 1);
                                screen.getChildren().add(new TreeView<>(new VueTrameHexa(current)));
                            }
                        });

        return table;
    }

    private MenuBar menu() {
        MenuBar appMenuBar = new MenuBar();
        Menu fileMenu = new Menu("Fichier");
        MenuItem open = new MenuItem("Ouvrir");
        MenuItem save = new MenuItem("Sauvegarder");

        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sauvegarder un fichier");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                FichierSauvegardeur fichierSauvegardeur = new FichierSauvegardeur();
                fichierSauvegardeur.sauvegarder(file.getPath(), trames);
            }
        });

        open.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir un fichier");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    List<ITrame> nouvellesTrames = new FichierExtracteur().extraireTrames(file.getPath());
                    trames.clear();
                    trames.addAll(nouvellesTrames);
                    current = trames.get(0);

                    screen.getChildren().remove(screen.getChildren().size() - 1);
                    screen.getChildren().add(new TreeView<>(new VueTrameHexa(current)));
                } catch (LigneMalFormatteeException ligneMalFormatteeException) {
                    Label errorLabel = new Label(ligneMalFormatteeException.getMessage());
                    Popup errorPopup = new Popup();

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
            }
        });

        fileMenu.getItems().add(open);
        fileMenu.getItems().add(save);
        appMenuBar.getMenus().add(fileMenu);
        return appMenuBar;
    }
}
