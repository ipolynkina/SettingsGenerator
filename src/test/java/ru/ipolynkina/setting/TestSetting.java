package ru.ipolynkina.setting;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.ipolynkina.beans.Parameter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSetting {

    private static Setting setting;
    private static List<String> correctProperty;
    private static List<String> correctProperty1;
    private static List<String> correctProperty2;

    @BeforeClass
    public static void init() {
        Parameter parameter = new Parameter("KPI", "164", true, 1, 2, "2", "0", "", "45", "1");
        List<String> shops = new ArrayList<>(Arrays.asList("A001"));
        setting = new Setting(LocalDate.of(2018, 1, 1), parameter, shops);

        correctProperty = new ArrayList<>(Setting.AMOUNT_PROPERTIES);
        correctProperty.add("01.01.2018");
        correctProperty.add("KPI");
        correctProperty.add("164");
        correctProperty.add("2");
        correctProperty.add("0");
        correctProperty.add("");
        correctProperty.add("45");
        correctProperty.add("45");
        correctProperty.add("45");
        correctProperty.add("1");
        correctProperty.add("A001");

        correctProperty1 = new ArrayList<>(Setting.AMOUNT_PROPERTIES);
        correctProperty1.add("01.01.2018");
        correctProperty1.add("KPI");
        correctProperty1.add("164/1");
        correctProperty1.add("2");
        correctProperty1.add("0");
        correctProperty1.add("");
        correctProperty1.add("45");
        correctProperty1.add("45");
        correctProperty1.add("45");
        correctProperty1.add("1");
        correctProperty1.add("A001");

        correctProperty2 = new ArrayList<>(Setting.AMOUNT_PROPERTIES);
        correctProperty2.add("01.01.2018");
        correctProperty2.add("KPI");
        correctProperty2.add("164/2");
        correctProperty2.add("2");
        correctProperty2.add("0");
        correctProperty2.add("");
        correctProperty2.add("45");
        correctProperty2.add("45");
        correctProperty2.add("45");
        correctProperty2.add("1");
        correctProperty2.add("A001");
    }

    @Test
    public void testNextSetting() {
        List<String> property = setting.getNextSetting();
        Assert.assertTrue(property.equals(correctProperty));

        property = setting.getNextSetting();
        Assert.assertTrue(property.equals(correctProperty1));

        property = setting.getNextSetting();
        Assert.assertTrue(property.equals(correctProperty2));
    }

    @AfterClass
    public static void testHasNext() {
        Assert.assertFalse(setting.hasNextSetting());
    }
}
