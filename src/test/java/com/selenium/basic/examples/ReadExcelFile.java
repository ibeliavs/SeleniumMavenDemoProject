package com.selenium.basic.examples;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;

public class ReadExcelFile {
    @Test
    public void writeToExcelFile() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Login");

        Row r0 = sheet.createRow(0);
        Cell c0 = r0.createCell(0);
        c0.setCellValue("Admin");

        File file = new File ("src/test/resources/testdata/ExcelWriteData.xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);

        workbook.write(outputStream);

        outputStream.close();
        workbook.close();

    }
    @Test
    public void readExcelFile() throws IOException {
        // Specify the path of file
        File src = new File("src/test/resources/testdata/TestData.xlsx");
        // load file
        FileInputStream fileInputStream = new FileInputStream(src);

        // Load workbook  HSSFWorkbook for xls file
        // and XSSFWorkbook for xlsx files
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
        // Load sheet. Here we are first sheet by name
        XSSFSheet sheet = wb.getSheet("Login");

        String data0 = sheet.getRow(0).getCell(0).getStringCellValue();
        String data1 = sheet.getRow(0).getCell(1).getStringCellValue();

        System.out.println(data0 + " " + data1);

        // Load sheet. Here we are first sheet by index
        XSSFSheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1){
            for(Cell cell : row) {
                switch(cell.getCellType()){
                    case STRING:
                        System.out.print(cell.getStringCellValue() + " ");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + " ");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + " ");
                        break;
                    default:
                        break;
                }
                System.out.println();
            }
        }

        fileInputStream.close();
        wb.close();
    }
    @Test
    public void readExcelSetData() throws IOException {

        File src = new File("src/test/resources/testdata/TestData.xlsx");
        FileInputStream fileInputStream = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);

        // Load sheet. Here we are first sheet by name
        XSSFSheet sheet = wb.getSheet("Login");

        int rowcount = sheet.getLastRowNum();
        System.out.println(rowcount);

        for(int i = 0; i<rowcount;i++){
            String data0 = sheet.getRow(i).getCell(0).getStringCellValue();
            System.out.println(data0);
        }

        fileInputStream.close();
        wb.close();
    }
}
