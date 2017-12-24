package ru.ipolynkina.writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import ru.ipolynkina.setting.Setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriterToXLS {

    private static final Logger LOGGER = LogManager.getLogger(WriterToXLS.class.getSimpleName());

    public static void writeToXLS(List<Setting> settings) throws IOException {
        try (FileInputStream fis = new FileInputStream("src/main/resources/excel/excel.xls");
             FileOutputStream fos = new FileOutputStream(new File("./settings.xls"));
             Workbook wb = new HSSFWorkbook(fis)) {

            int indexRow = 1;
            for(Setting setting : settings) {
                while(setting.hasNextSetting()) {
                    Row row = wb.getSheetAt(0).createRow(indexRow);
                    Cell cell;
                    List<String> property = setting.getNextSetting();
                    for(int indexProperty = 0; indexProperty < Setting.AMOUNT_PROPERTIES; ++indexProperty) {
                        cell = row.createCell(indexProperty);
                        cell.setCellValue(property.get(indexProperty));
                    }
                    ++indexRow;
                }
            }
            wb.write(fos);
        } catch (IOException exc) {
            LOGGER.warn("current directory: " + new File(".").getAbsolutePath());
            LOGGER.warn(exc.getMessage());
            throw new IOException(exc.getMessage());
        }
    }
}
