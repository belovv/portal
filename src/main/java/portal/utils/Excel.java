package portal.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class Excel {

    public Excel() {
    }

    public void readAllExcel(File excelFile) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            for (int i = 0; i < row.getLastCellNum(); i++) {
                System.out.printf("%8s", row.getCell(i));
            }
            System.out.println();
        }
    }

    public void checkSmallExcelHaveNoBlank(File excelFile) {
        InputStream inputStream = null;
        Workbook workbook = null;
        Robot robot = null;
        try {
            inputStream = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        for (int j = 0; j < 5; j++) {
            row = sheet.getRow(j);
            for (int i = 0; i < 8; i++) {
                assert row.getCell(i) != null;
            }
        }
        // Закрываем всплывающее окно Windows TODO придумать что то кроме  Thread.sleep(5000);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);  // VK_WINDOWS key still pressed
        robot.keyRelease(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

}

