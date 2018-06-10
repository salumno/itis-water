package ru.kpfu.itis.water.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.water.model.Appointment;
import ru.kpfu.itis.water.model.FileInfo;

import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AppointmentDocsGenerator {

    @Value("${storage.path.appointment.docs}")
    private String appointmentDocsStoragePath;

    private DateTimeFormatter dateTimeFormatter;
    private Font cyrillicFont;

    @SneakyThrows
    public AppointmentDocsGenerator() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        BaseFont bf = BaseFont.createFont("/home/salumno/IdeaProjects/itis-water/src/main/resources/static/fonts/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        cyrillicFont = new Font(bf,10, Font.NORMAL);
    }

    public FileInfo generateDocByAppointment(Appointment appointment) {
        FileInfo docFileInfo = generateDocFileInfo();
        writeDocToStorage(docFileInfo, appointment);
        return docFileInfo;
    }

    private FileInfo generateDocFileInfo() {
        String docStorageName = UUID.randomUUID().toString() + ".pdf";
        return FileInfo.builder()
                .storageFileName(docStorageName)
                .path(appointmentDocsStoragePath + "/" + docStorageName)
                .type("application/pdf")
                .build();
    }

    @SneakyThrows
    private void writeDocToStorage(FileInfo fileInfo, Appointment appointment) {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(document, new FileOutputStream(fileInfo.getPath()));
        document.open();
        document.add(generateAppointmentHeader());
        document.add(generateAppointmentTable(appointment));
        document.close();
    }

    private Paragraph generateAppointmentHeader() {
        return new Paragraph("Вы успешно записались на прием!", cyrillicFont);
    }

    private PdfPTable generateAppointmentTable(Appointment appointment) {
        PdfPTable table = new PdfPTable(2);

        table.setSpacingBefore(25);
        table.setSpacingAfter(25);

        table.addCell(new Paragraph(""));
        table.addCell(new Paragraph(appointment.getCode(), cyrillicFont));

        table.addCell(new Paragraph("Ваше имя: ", cyrillicFont));
        table.addCell(new Paragraph(appointment.getUser().getSurname() + " " + appointment.getUser().getName(), cyrillicFont));

        table.addCell(new Paragraph("Описание: ", cyrillicFont));
        table.addCell(new Paragraph(appointment.getDescription(), cyrillicFont));

        table.addCell(new Paragraph("Отдел: ", cyrillicFont));
        table.addCell(new Paragraph(appointment.getDepartment().getName(), cyrillicFont));

        table.addCell(new Paragraph("Адрес: ", cyrillicFont));
        table.addCell(new Paragraph(appointment.getDepartment().getAddress(), cyrillicFont));

        table.addCell(new Paragraph("Дата и время: ", cyrillicFont));
        table.addCell(new Paragraph(appointment.getDateTime().format(dateTimeFormatter), cyrillicFont));

        return table;
    }
}
