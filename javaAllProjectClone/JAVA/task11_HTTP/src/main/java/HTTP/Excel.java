package HTTP;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    public void readFromJsonToList(String link) throws IOException {
        Type listType = new TypeToken<ArrayList<Ticket>>() {}.getType();
        List<Ticket> linksList = new Gson().fromJson(new FileReader(link), listType);
        createExcel(linksList);
    }

    public void createExcel(List<Ticket> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet 1");
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(list.get(i).getCategory());
            row.createCell(1).setCellValue(list.get(i).getDate());
            row.createCell(2).setCellValue(list.get(i).getId());
            row.createCell(3).setCellValue(list.get(i).getPlace());
            row.createCell(4).setCellValue(list.get(i).getTitle());
        }
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();
    }
}
