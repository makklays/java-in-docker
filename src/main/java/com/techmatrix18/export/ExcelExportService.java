package com.techmatrix18.export;

import com.techmatrix18.model.Unit;
import com.techmatrix18.model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    /**
     * Use for list of Users
     *
     * @param users
     * @return
     * @throws IOException
     */
    public ByteArrayInputStream exportUsersToExcel(List<User> users) throws IOException {
        try (Workbook workbook = new SXSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users");

            // headers
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Username");
            headerRow.createCell(2).setCellValue("Mobile");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Gender");
            headerRow.createCell(5).setCellValue("Age");

            // data
            int rowIdx = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(user.getMob());
                row.createCell(3).setCellValue(user.getEmail());
                row.createCell(4).setCellValue(user.getGender());
                row.createCell(5).setCellValue(user.getAge());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    /**
     * Use for list of Units
     *
     * @param units
     * @return
     * @throws IOException
     */
    public ByteArrayInputStream exportUnitsToExcel(List<Unit> units) throws IOException {
        try (Workbook workbook = new SXSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Units");

            // headers
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Type");
            headerRow.createCell(3).setCellValue("Res Agua");
            headerRow.createCell(4).setCellValue("Res Plastic");
            headerRow.createCell(5).setCellValue("Res Food");
            headerRow.createCell(6).setCellValue("Res Iron");
            headerRow.createCell(7).setCellValue("Nivel");

            // data
            int rowIdx = 1;
            for (Unit unit : units) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(unit.getId());
                row.createCell(1).setCellValue(unit.getTitle());
                row.createCell(2).setCellValue(unit.getType());
                row.createCell(3).setCellValue(unit.getResAgua());
                row.createCell(4).setCellValue(unit.getResPlastic());
                row.createCell(5).setCellValue(unit.getResFood());
                row.createCell(6).setCellValue(unit.getResIron());
                row.createCell(7).setCellValue(unit.getLevel());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}

