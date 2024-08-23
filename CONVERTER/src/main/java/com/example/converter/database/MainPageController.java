package com.example.converter.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

        @FXML
        private Button clear_btn;

        @FXML
        private Button del_btn;

        @FXML
        private Button NUMBER0;

        @FXML
        private Button NUMBER00;

        @FXML
        private Button NUMBER1;

        @FXML
        private Button NUMBER2;

        @FXML
        private Button NUMBER3;

        @FXML
        private Button NUMBER4;

        @FXML
        private Button NUMBER5;

        @FXML
        private Button NUMBER6;

        @FXML
        private Button NUMBER7;

        @FXML
        private Button NUMBER8;

        @FXML
        private Button NUMBER9;

        @FXML
        private TextField choiceT1;

        @FXML
        private TextField DisplayLabel;

        @FXML
        private ChoiceBox<String> f_currency;

        @FXML
        private ChoiceBox<String> s_currency;

        private final Map<String, Double> conversionRates = new HashMap<>();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                //
                ObservableList<String> currencies = FXCollections.observableArrayList("POUNDS", "USD","EURO","CEDI","NAIRA");
                f_currency.setItems(currencies);
                s_currency.setItems(currencies);

                //Initializing conversion rates
                conversionRates.put("CEDI", 15.67);
                conversionRates.put("USD", 1.0);
                conversionRates.put("EURO", 0.85);
                conversionRates.put("POUNDS", 0.75);

                // Set default selection
                f_currency.setValue("USD");
                s_currency.setValue("CEDI");

                f_currency.getSelectionModel().selectedItemProperty().addListener((obs, oldVal,newVal) -> updateConversion());
                s_currency.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateConversion());
                choiceT1.textProperty().addListener((obs, oldVal, newVal) -> updateConversion());

                del_btn.setOnAction(event -> onDelete());
                NUMBER00.setOnAction(event -> onDotPressed());
                NUMBER0.setOnAction(event -> onZeroPressed());
                NUMBER1.setOnAction(event -> onOnePressed());
                NUMBER2.setOnAction(event -> onTwoPressed());
                NUMBER3.setOnAction(event -> onThreePressed());
                NUMBER4.setOnAction(event -> onFourPressed());
                NUMBER5.setOnAction(event -> onFivePressed());
                NUMBER6.setOnAction(event -> onSixPressed());
                NUMBER7.setOnAction(event -> onSevenPressed());
                NUMBER8.setOnAction(event -> onEightPressed());
                NUMBER9.setOnAction(event -> onNinePressed());
                clear_btn.setOnAction(event -> clearForm());
        }

        public void onDelete() {
                String currentText =  choiceT1.getText();
                if (!currentText.isEmpty()) {
                        choiceT1.setText(currentText.substring(0, currentText.length() - 1));
                }
        }

        private void updateConversion() {
                String fromCurrency = f_currency.getValue();
                String toCurrency = s_currency.getValue();
                String amountText = choiceT1.getText();

                if(f_currency != null && s_currency != null && !amountText.isEmpty()) {
                        try {
                                double amount = Double.parseDouble(amountText);
                                double fromRate = conversionRates.getOrDefault(fromCurrency, 1.0);
                                double toRate = conversionRates.getOrDefault(toCurrency, 1.0);
                                double convertedAmount = amount * (toRate / fromRate);
                                DisplayLabel.setText(String.format("%.2f", convertedAmount));
                        } catch (NumberFormatException e) {
                                DisplayLabel.setText("Invalid Input");
                        }
                }else {
                        DisplayLabel.clear();
                }
        }

        public void onDotPressed() {
                choiceT1.appendText(".");
        }

        public void onZeroPressed() {
                choiceT1.appendText("0");
        }

        public void onOnePressed() {
                choiceT1.appendText("1");
        }

        public void onTwoPressed() {
                choiceT1.appendText("2");
        }

        public void onThreePressed() {
                choiceT1.appendText("3");
        }

        public void onFourPressed() {
                choiceT1.appendText("4");
        }

        public void onFivePressed() {
                choiceT1.appendText("5");
        }

        public void onSixPressed() {
                choiceT1.appendText("6");
        }

        public void onSevenPressed() {
                choiceT1.appendText("7");
        }

        public void onEightPressed() {
                choiceT1.appendText("8");
        }

        public void onNinePressed() {
                choiceT1.appendText("9");
        }
        private void clearForm () {
                choiceT1.clear();
        }
}


