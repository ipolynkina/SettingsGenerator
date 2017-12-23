package ru.ipolynkina.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label lblDate;

    @FXML
    private DatePicker dpBeginDate;

    @FXML
    private CheckBox chkSetOriginalSettings;

    @FXML
    private Label lblKoef;

    @FXML
    private ComboBox cbbKoef;

    @FXML
    private Label lblShops;

    @FXML
    private TextArea txtShops;

    @FXML
    private Button btnGenerate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpBeginDate.setValue(LocalDate.now());
    }
}
