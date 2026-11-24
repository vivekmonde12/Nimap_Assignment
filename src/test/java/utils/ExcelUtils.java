package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getData(String sheetName) {
        String path = "src/test/resources/testdata.xlsx";
        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet(sheetName);
            DataFormatter fmt = new DataFormatter();   // reads phone numbers as text, not 9.87E9
            int rows = sheet.getLastRowNum();          // excludes header row
            int cols = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rows][cols];

            for (int i = 1; i <= rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = (row == null) ? null : row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : fmt.formatCellValue(cell);
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel file", e);
        }
    }
}