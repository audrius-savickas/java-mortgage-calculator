package DataWindow;

import Main.Linijinis;
import Main.MonthData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;

public class FileCreation {
    static Writer writer = null;
    public static boolean outputFile(MonthData [] data) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Paskolos ataskaita");
        sheet.setColumnWidth(0, 256*8);
        sheet.setColumnWidth(1, 256*10);
        sheet.setColumnWidth(2, 256*18);
        sheet.setColumnWidth(3, 256*18);
        sheet.setColumnWidth(4, 256*18);
        sheet.setColumnWidth(5, 256*23);

        Row row = sheet.createRow(0);
        XSSFFont rowFont = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        rowFont.setBold(true);
        style.setFont(rowFont);
        row.setRowStyle(style);

        row.createCell(0).setCellValue("METAI");
        row.createCell(1).setCellValue("MĖNUO");
        row.createCell(2).setCellValue("PASKOLOS ĮMOKA");
        row.createCell(3).setCellValue("PALŪKANŲ ĮMOKA");
        row.createCell(4).setCellValue("BENDRA ĮMOKA");
        row.createCell(5).setCellValue("PASKOLOS LIKO");
        for (int i = 0; i < 6; i++){
            row.getCell(i).setCellStyle(style);
        }
        for (int i = 0; i < Linijinis.kiekMen; i++){
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data[i].getMetai());
            row.createCell(1).setCellValue(data[i].getMenuo());
            row.createCell(2).setCellValue(data[i].getMoketiSuma());
            row.createCell(3).setCellValue(data[i].getProcentuMoketi());
            row.createCell(4).setCellValue(data[i].getVisoMoketi());
            row.createCell(5).setCellValue(data[i].getLikoNesumoketa());
        }
        try (FileOutputStream outputStream = new FileOutputStream("ataskaita.xlsx")) {
            workbook.write(outputStream);
        }
        catch (IOException e){
            throw e;
        }
        return true;
    }
}