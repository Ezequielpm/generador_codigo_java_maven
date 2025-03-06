package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    @FXML
    private VBox databaseContainer;

    private final List<String> databases = new ArrayList<>();

    @FXML
    public void initialize() {
        refreshDatabaseView();
    }

    @FXML
    private void showDatabasePopup() throws IOException {
        // Obtener el Stage principal desde cualquier nodo (en este caso, desde el VBox)
        Stage mainStage = (Stage) databaseContainer.getScene().getWindow();

        // Mostrar el popup usando el método helper centralizado
        DatabasePopupController popup = DatabasePopupController.showPopup(mainStage);

        if (popup.isDatabaseCreated()) {
            databases.add(popup.getDatabaseDetails());
            refreshDatabaseView();
        }
    }

    private void refreshDatabaseView() {
        databaseContainer.getChildren().clear();

        if (databases.isEmpty()) {
            databaseContainer.getChildren().add(new Label("Here will be databases when you add someone."));
        } else {
            for (String db : databases) {
                HBox row = new HBox(10);
                row.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 5;");

                Label nameLabel = new Label(db);
                Button viewButton = new Button("View");
                Button editButton = new Button("Edit");
                Button deleteButton = new Button("Delete");

                row.getChildren().addAll(nameLabel, viewButton, editButton, deleteButton);
                databaseContainer.getChildren().add(row);
            }
        }
    }
}