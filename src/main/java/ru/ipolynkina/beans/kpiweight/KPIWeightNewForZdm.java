package ru.ipolynkina.beans.kpiweight;

import ru.ipolynkina.beans.Parameter;

public class KPIWeightNewForZdm extends Parameter {

    public KPIWeightNewForZdm(String coefficient, String post, Boolean useWithoutCategory, Integer minCategory, Integer maxCategory,
                              String amount, String type, String nomenclature, String value, String activate) {
        super(coefficient, post, useWithoutCategory, minCategory, maxCategory, amount, type, nomenclature, value, activate);
    }
}
