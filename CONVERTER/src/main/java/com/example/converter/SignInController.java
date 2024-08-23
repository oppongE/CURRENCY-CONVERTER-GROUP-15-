package com.example.converter;

import com.dlsc.formsfx.model.structure.PasswordField;
import com.example.converter.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInController {

   @FXML
    private TextField CpasswordField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void handleSignUP() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String cPassword = CpasswordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields");
            return;
        }

        if (!password.equals(cPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Confirm Password mismatch");
            return;
        }

        User user = new User(username, password);


        if (registerUser(user)) {

            showAlert(Alert.AlertType.INFORMATION, "Sign Up", "Sign Up Successful");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Currency Converter Application");
                stage.show();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            clearForm();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
        }
        else{
            showAlert(Alert.AlertType.ERROR, "Error", "Sign Up Failed");
        }
    }

    @FXML
    void handleCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Sing Up");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel the Sign Up?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
        }
    }

    private boolean registerUser(User user) {
        String sql = "INSERT INTO USERS (username, password) VALUES(?, ?)";

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        usernameField.clear();
        passwordField.clear();
        CpasswordField.clear();
    }

}
