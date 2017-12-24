package ru.ipolynkina.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.ipolynkina.beans.Coefficients;
import ru.ipolynkina.beans.kpiweight.*;
import ru.ipolynkina.main.Main;
import ru.ipolynkina.setting.Setting;
import ru.ipolynkina.writer.WriterToXLS;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpBeginDate.setValue(LocalDate.now());
        Coefficients coefficients = Main.ctx.getBean(Coefficients.class);
        cbbCoefficient.getItems().setAll(coefficients.getCoefficients());
        cbbCoefficient.setValue(coefficients.getCoefficientByIndex(0));
    }

    @FXML
    private void generate() {
        if(dpBeginDate.getValue() == null) return;
        if(txtShops.getText().equals("")) return;

        List<String> shops = new ArrayList<>();
        shops.add(txtShops.getText());

        createSettingsKPIWeightNew(shops);
        if(chkSetOriginalSettings.isSelected()) {
            createSettingsKPIWeightOld(shops);
        }

        WriterToXLS.writeToXLS(settings);
    }

    private void createSettingsKPIWeightNew(List<String> shops) {
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForDm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForZdm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForZdmTo.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightNewForZdmNto.class), shops));
    }

    private void createSettingsKPIWeightOld(List<String> shops) {
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightOldForDm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightOldForZdm.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightOldForZdmTo.class), shops));
        settings.add(new Setting(dpBeginDate.getValue(), Main.ctx.getBean(KPIWeightOldForZdmNto.class), shops));
    }
}
