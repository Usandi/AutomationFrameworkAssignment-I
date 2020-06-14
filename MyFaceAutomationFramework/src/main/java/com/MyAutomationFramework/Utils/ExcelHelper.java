package com.MyAutomationFramework.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelHelper {
    private static Logger log = LogManager.getLogger(ExcelHelper.class);

    public static Object[][] importExcelData(String filename, String sheetname) {
        Object[][] data = null;
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(Constants.RESOURCES_PATH + "/DataForSigninFile/" + filename);
            XSSFSheet sheet = wb.getSheet(sheetname);
            int rowsIndex =  sheet.getLastRowNum();
            log.info("Total rows: " + rowsIndex);
            data = new Object[rowsIndex][];
            for (int i=1; i<= rowsIndex; i++) {
                XSSFRow row = sheet.getRow(i);
                int columns = row.getLastCellNum();
                log.info("Total columns: "+ columns);
                Object[] colData = new Object[columns];
                for (int j = 0; j < columns; j++) {
                    CellType ctype = sheet.getRow(i).getCell(j).getCellType();//get cell type
                    if (ctype == ctype.NUMERIC) {
                        colData[j] = (int)row.getCell(j).getNumericCellValue();// if the data is number convert it to int from double
                    }
                    else {
                        colData[j] = row.getCell(j).toString();
                    }
                }
                data[i - 1] = colData;
            }
        } catch (IOException e) {
            log.error("ExelHelper exception" + e);
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
        return data;
    }
}
