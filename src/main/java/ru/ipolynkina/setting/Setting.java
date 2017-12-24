package ru.ipolynkina.setting;

import ru.ipolynkina.beans.Parameter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Setting {

    private LocalDate date;
    private Parameter parameter;
    private List<String> shops;

    public Setting(LocalDate date, Parameter parameter, List<String> shops) {
        this.date = date;
        this.parameter = parameter;
        this.shops = shops;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getDate().toString());
        builder.append(" " + parameter.toString());
        builder.append(" " + Arrays.toString(shops.toArray()));
        return builder.toString();
    }
}
