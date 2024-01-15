package com.example.service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/////Excel 表格导出service层

@Service
public class ExcelExportService {

    public void exportToExcel(String filePath) {
        // 数据源（假设是从数据库中获取的数据）
        List<List<String>> data = Arrays.asList(
                Arrays.asList("Name", "Age", "Email"),
                Arrays.asList("John Doe", "25", "john@example.com"),
                Arrays.asList("Jane Smith", "30", "jane@example.com")
        );

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // 写入数据
            int rowNum = 0;
            for (List<String> rowData : data) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (String cellData : rowData) {
                    Cell cell = row.createCell(colNum++);
                    cell.setCellValue(cellData);
                }
            }

            // 保存文件
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
