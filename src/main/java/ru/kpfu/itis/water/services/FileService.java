package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.model.FileInfo;

import javax.servlet.http.HttpServletResponse; /**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface FileService {
    void writeAppointmentDocToResponse(Long appointmentDocId, HttpServletResponse response);

    void writeImageToResponse(Long imageId, HttpServletResponse response);

    void writeEmployeesToResponse(FileInfo employeeDocumentInfo, HttpServletResponse response);
}
