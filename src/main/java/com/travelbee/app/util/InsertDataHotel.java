package com.travelbee.app.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsertDataHotel {
    public static List<Object[]> readExcel(String filePath) throws IOException {
        List<Object[]> objects = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Lặp qua từng sheet
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // Lặp qua từng hàng
            for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                // Tạo một mảng Object[] cho hàng hiện tại
                Object[] rowObjects = new Object[row.getPhysicalNumberOfCells()];

                // Lặp qua từng ô trong hàng
                for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);

                    // Đọc giá trị của ô
                    Object value = getCellValue(cell);

                    // Thêm giá trị vào mảng Object[]
                    rowObjects[cellIndex] = value;
                }

                // Thêm mảng Object[] vào danh sách
                objects.add(rowObjects);
            }
        }

        return objects;
    }

    private static Object getCellValue(Cell cell) {
        // Lấy kiểu dữ liệu của ô
        CellType cellType = cell.getCellType();

        // Đọc giá trị của ô theo kiểu dữ liệu
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

}
