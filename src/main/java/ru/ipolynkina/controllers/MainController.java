package ru.ipolynkina.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ipolynkina.beans.Coefficients;
import ru.ipolynkina.beans.kpiweight.*;
import ru.ipolynkina.main.Main;
import ru.ipolynkina.setting.Setting;
import ru.ipolynkina.writer.WriterToXLS;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label lblDate, lblCoefficient, lblShops;

    @FXML
    private DatePicker dpBeginDate;

    @FXML
    private CheckBox chkSetOriginalSettings;

    @FXML
    private ComboBox cbbCoefficient;

    @FXML
    private TextArea txtShops;

    @FXML
    private Button btnGenerate;

    private List<Setting> settings = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger(MainController.class.getSimpleName());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpBeginDate.setValue(LocalDate.now());
        Coefficients coefficients = Main.ctx.getBean(Coefficients.class);
        cbbCoefficient.getItems().setAll(coefficients.getCoefficients());
        cbbCoefficient.setValue(coefficients.getCoefficientByIndex(0));
    }

    @FXML
    private void generate() {
        LOGGER.info("begin date: " + dpBeginDate.getValue());
        LOGGER.info("return old values: " + chkSetOriginalSettings.isSelected());
        LOGGER.info("coefficient: " + cbbCoefficient.getValue());
        LOGGER.info("shops: " + txtShops.getParagraphs());

        if(dpBeginDate.getValue() == null) return;
        if(txtShops.getText().equals("")) return;

        LOGGER.info("generation start");
        List<String> shops = new ArrayList<>();
        shops.addAll(Arrays.asList(txtShops.getText().split("\n")));

        createSettingsKPIWeightNew(shops);
        if(chkSetOriginalSettings.isSelected()) {
            createSettingsKPIWeightOld(shops);
        }

        try {
            WriterToXLS.writeToXLS(settings);
            showOkDialog("Генератор настроек для TS", "Запись в файл успешно завершена", "Статус: ОК");
            LOGGER.info("generation end");
        } catch (IOException exc) {
            showErrorDialog("Генератор настроек для TS", exc.getMessage(), "Статус: ОШИБКА");
        }
    }

    private void createSettingsKPIWeightNew(List<String> shops) {
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForDm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForZdm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForZdmTo.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForZdmNto.class), shops));
    }

    private void createSettingsKPIWeightOld(List<String> shops) {
        settings.add(new Setting(dpBeginDate.getValue().plusMonths(1), Main.ctx.getBean(KPIWeightOldForDm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue().plusMonths(1), Main.ctx.getBean(KPIWeightOldForZdm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue().plusMonths(1), Main.ctx.getBean(KPIWeightOldForZdmTo.class), shops));
        settings.add(new Setting(dpBeginDate.getValue().plusMonths(1), Main.ctx.getBean(KPIWeightOldForZdmNto.class), shops));
    }

    private void showOkDialog(String title, String header, String context) {
        LOGGER.info("show ok dialog");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

    private void showErrorDialog(String title, String header, String context) {
        LOGGER.info("show error dialog");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
