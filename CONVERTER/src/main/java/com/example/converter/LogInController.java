package com.example.converter;

import com.dlsc.formsfx.model.structure.PasswordField;
import com.example.converter.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {

    public javafx.scene.control.PasswordField passField;

    @FXML
    private TextField userField;



    @FXML
    private void handleLogin() {
        String username = userField.getText();
        String password = passField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Fill in all fields");
            return;
        }
        if (auth_user(username,password)) {
            showAlert(Alert.AlertType.INFORMATION, "", "Login Successful");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Currency Converter Application");
                stage.show();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Stage stage = (Stage) userField.getScene().getWindow();
            stage.close();
        }else {
            showAlert(Alert.AlertType.ERROR, "Error", "Login Failed");
        }
    }

    @FXML
    private void handleCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Sing Up");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel the Sign Up?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) userField.getScene().getWindow();

            stage.close();
        }
    }

    private boolean auth_user(String user, String password) {
        String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement prepared = conn.prepareStatement(sql)) {

            prepared.setString(1, user);
            prepared.setString(2, password);

            ResultSet rs = prepared.executeQuery();

            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        userField.clear();
        passField.clear();
    }
}
