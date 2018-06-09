package ru.kpfu.itis.water.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.water.dto.EmployeeDto;
import ru.kpfu.itis.water.model.FileInfo;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class EmployeeDocsGenerator {
    @Value("${storage.path.employee.docs}")
    private String employeeDocsStoragePath;

    private DateTimeFormatter dateTimeFormatter;
    private Font cyrillicFont;

    @SneakyThrows
    public EmployeeDocsGenerator() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        BaseFont bf = BaseFont.createFont("/home/salumno/IdeaProjects/itis-water/src/main/resources/static/fonts/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        cyrillicFont = new Font(bf,10, Font.NORMAL);
    }

    public FileInfo generateDoc(List<EmployeeDto> employees) {
        FileInfo docFileInfo = generateDocFileInfo();
        writeDocToStorage(docFileInfo, employees);
        return docFileInfo;
    }

    private FileInfo generateDocFileInfo() {
        String docStorageName = UUID.randomUUID().toString() + ".pdf";
        return FileInfo.builder()
                .storageFileName(docStorageName)
                .path(employeeDocsStoragePath + "/" + docStorageName)
                .type("application/pdf")
                .build();
    }

    @SneakyThrows
    private void writeDocToStorage(FileInfo docFileInfo, List<EmployeeDto> employees) {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(document, new FileOutputStream(docFileInfo.getPath()));
        document.open();
        document.add(generateDocHeader());
        document.add(generateEmployeeTable(employees));
        document.close();
    }

    private Paragraph generateDocHeader() {
        return new Paragraph("Список сотрудников на " + LocalDateTime.now().format(dateTimeFormatter), cyrillicFont);
    }

    private PdfPTable generateEmployeeTable(List<EmployeeDto> employees) {
        PdfPTable table = new PdfPTable(3);

        table.setSpacingBefore(25);
        table.setSpacingAfter(25);

        table.addCell(new Paragraph("ФИО", cyrillicFont));
        table.addCell(new Paragraph("Email", cyrillicFont));
        table.addCell(new Paragraph("Роль", cyrillicFont));

        for (EmployeeDto employee: employees) {
            String employeeFullName = employee.getSurname() + " " + employee.getName() + " " + employee.getPatro();
            table.addCell(new Paragraph(employeeFullName, cyrillicFont));
            table.addCell(new Paragraph(employee.getEmail(), cyrillicFont));
            table.addCell(new Paragraph(employee.getRole().toString(), cyrillicFont));
        }

        return table;
    }
}
