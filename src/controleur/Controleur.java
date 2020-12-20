package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.file.input.FichierExtracteur;
import model.network.INetworkObject;
import model.network.champ.Champ;
import model.network.champ.IChamp;
import model.network.champ.ListeChamps;
import model.network.entete.Entete;
import model.network.entete.IEntete;
import model.network.trame.ITrame;
import model.network.trame.Trame;
import vue.trame.VueNetworkObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
        timeCol.setCellValueFactory(new PropertyValueFactory<>("temps"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        protoCol.setCellValueFactory(new PropertyValueFactory<>("protocoleAbreviation"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        infoCol.setCellValueFactory(new PropertyValueFactory<>("info"));

        table.setItems(trames);

        table
                .getColumns()
                .addAll(noCol, timeCol, sourceCol, destCol, protoCol, lengthCol, infoCol);

        return table;
    }

    private MenuBar menu() {
        MenuBar appMenuBar = new MenuBar();
        Menu fileMenu = new Menu("Fichier");
        MenuItem open = new MenuItem("Ouvrir");

        open.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir un fichier");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                List<ITrame> nouvellesTrames = new FichierExtracteur().extraireTrames(file.getPath());
                trames.addAll(nouvellesTrames);
                current = trames.get(0);

                screen.getChildren().add(new TreeView<>(new VueNetworkObject(current)));
                System.out.println(current);
            }
        });

        fileMenu.getItems().add(open);
        appMenuBar.getMenus().add(fileMenu);
        return appMenuBar;
    }
}
