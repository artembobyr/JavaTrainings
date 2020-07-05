import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelHelper {
    static XSSFWorkbook wb;
    static XSSFSheet sheet;

    public static void setExcel(String path, int sheetNum) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheetAt(sheetNum);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static String getData(int rowNum, int sellNum) {
        String data = sheet.getRow(rowNum).getCell(sellNum).getStringCellValue();
        return data;
    }
}
