package com.techmatrix18.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph; // если используешь текст
import com.itextpdf.text.pdf.PdfPCell;
import com.techmatrix18.model.Unit;
import com.techmatrix18.model.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfExportService {

    /**
     * Use for list of Users
     *
     * @param users
     * @return
     */
    public ByteArrayInputStream exportUsersToPdf(List<User> users) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            PdfPTable table = new PdfPTable(3);
            table.addCell("ID");
            table.addCell("Username");
            table.addCell("Mobile");
            table.addCell("Email");
            table.addCell("Gender");
            table.addCell("Age");

            for (User user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getUsername());
                table.addCell(user.getMob());
                table.addCell(user.getEmail());
                table.addCell(user.getGender());
                table.addCell(user.getAge());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error while exporting to PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    /**
     * Use for list of Units
     *
     * @param units
     * @return
     */
    public ByteArrayInputStream exportUnitsToPdf(List<Unit> units) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            PdfPTable table = new PdfPTable(3);
            table.addCell("ID");
            table.addCell("Title");
            table.addCell("Type");
            table.addCell("Res Agua");
            table.addCell("Res Plastic");
            table.addCell("Res Food");
            table.addCell("Res Iron");
            table.addCell("Nivel");

            for (Unit unit : units) {
                table.addCell(String.valueOf(unit.getId()));
                table.addCell(unit.getTitle());
                table.addCell(unit.getType());
                table.addCell(String.valueOf(unit.getResAgua()));
                table.addCell(String.valueOf(unit.getResPlastic()));
                table.addCell(String.valueOf(unit.getResFood()));
                table.addCell(String.valueOf(unit.getResIron()));
                table.addCell(String.valueOf(unit.getLevel()));
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error while exporting to PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

