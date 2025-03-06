package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DatabasePopupController {

    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private TextField databaseField;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private boolean databaseCreated = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isDatabaseCreated() {
        return databaseCreated;
    }

    public String getDatabaseDetails() {
        return String.format("IP: %s, Port: %s, Database: %s, User: %s",
                ipField.getText().trim(),
                portField.getText().trim(),
                databaseField.getText().trim(),
                userField.getText().trim());
    }

    @FXML
    private void cancel() {
        databaseCreated = false;
        stage.close();
    }

    @FXML
    private void create() {
        if (validateFields()) {
            databaseCreated = true;
            stage.close();
        } else {
            System.out.println("Please fill in all fields."); // Aquí puedes poner un Alert si prefieres.
        }
    }

    private boolean validateFields() {
        return !ipField.getText().trim().isEmpty() &&
                !portField.getText().trim().isEmpty() &&
                !databaseField.getText().trim().isEmpty() &&
                !userField.getText().trim().isEmpty() &&
                !passwordField.getText().trim().isEmpty();
    }

    // 🔥 Este es el método estático que centraliza todo el showPopup
    public static DatabasePopupController showPopup(Stage parentStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(DatabasePopupController.class.getResource("/views/DatabasePopup.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(DatabasePopupController.class.getResource("/styles/databasePopup.css").toExternalForm());

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(parentStage);
        popupStage.setScene(scene);
        //popupStage.setTitle("Add New Database");

        DatabasePopupController controller = loader.getController();
        controller.setStage(popupStage);

        popupStage.showAndWait();

        return controller;
    }
}