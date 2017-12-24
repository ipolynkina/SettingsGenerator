package ru.ipolynkina.setting;

import ru.ipolynkina.beans.Parameter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Setting {

    public static final int AMOUNT_PROPERTIES = 11;

    private LocalDate date;
    private Parameter parameter;
    private List<String> shops;
    private int amountSettings;

    private int counterCategory;
    private int counterShop = 0;
    private int counterSettings = 0;

    public Setting(LocalDate date, Parameter parameter, List<String> shops) {
        this.date = date;
        this.parameter = parameter;
        this.shops = shops;
        amountSettings = shops.size() * (parameter.getMaxCategory() - parameter.getMinCategory() + 1);
        if(parameter.getUseWithoutCategory()) amountSettings += shops.size();
        counterCategory = parameter.getMinCategory();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(date.toString());
        builder.append(" " + parameter.toString());
        builder.append(" " + Arrays.toString(shops.toArray()));
        return builder.toString();
    }

    public boolean hasNextSetting() {
        return counterSettings < amountSettings;
    }

    public List<String> getNextSetting() {
        List<String> setting = new ArrayList<>(AMOUNT_PROPERTIES);

        String day = date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : String.valueOf(date.getDayOfMonth());
        String month = date.getMonthValue() < 10 ? "0" + date.getMonthValue() : String.valueOf(date.getMonthValue());
        String currentDate = day + "." + month + "." + date.getYear();

        setting.add(currentDate);
        setting.add(parameter.getCoefficient());
        if(parameter.getUseWithoutCategory() &&
                (counterSettings == 0 || counterSettings % (parameter.getMaxCategory() + 1) == 0)) {
            setting.add(parameter.getPost());
        } else {
            setting.add(parameter.getPost() + "/" + counterCategory);
            if(++counterCategory > parameter.getMaxCategory()) counterCategory = parameter.getMinCategory();
        }
        setting.add(parameter.getAmount());
        setting.add(parameter.getType());
        setting.add(parameter.getNomenclature());
        setting.add(parameter.getValue());
        setting.add(parameter.getValue());
        setting.add(parameter.getValue());
        setting.add(parameter.getActivate());
        setting.add(shops.get(counterShop));

        int addRow = 0;
        if(parameter.getUseWithoutCategory()) ++addRow;
        if((counterSettings != 0) &&
                ((counterSettings + 1) % (parameter.getMaxCategory() - parameter.getMinCategory() + 1 + addRow) == 0)) {
            ++counterShop;
        }

        ++counterSettings;
        return setting;
    }
}
