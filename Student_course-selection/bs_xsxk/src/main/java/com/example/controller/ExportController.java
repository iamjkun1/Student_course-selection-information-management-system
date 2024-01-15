package com.example.controller;

import com.example.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

////Excel 表格导出控制层

@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/excel")
    @ResponseBody
    public String exportToExcel() {
        // 导出文件路径
        String filePath = "exported_data.xlsx";

        // 调用 Excel 导出服务
        excelExportService.exportToExcel(filePath);

        return "Excel file exported successfully!";
    }

    @GetMapping("/download/excel")
    public ResponseEntity<File> downloadExcel() {
        String filePath = "exported_data.xlsx";
        File file = new File(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exported_data.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }
}
