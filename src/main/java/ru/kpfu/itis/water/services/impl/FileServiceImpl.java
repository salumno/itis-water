package ru.kpfu.itis.water.services.impl;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.model.Appointment;
import ru.kpfu.itis.water.model.FileInfo;
import ru.kpfu.itis.water.model.Image;
import ru.kpfu.itis.water.repositories.AppointmentRepository;
import ru.kpfu.itis.water.repositories.ImageRepository;
import ru.kpfu.itis.water.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class FileServiceImpl implements FileService {

    private ImageRepository imageRepository;
    private AppointmentRepository appointmentRepository;

    public FileServiceImpl(ImageRepository imageRepository, AppointmentRepository appointmentRepository) {
        this.imageRepository = imageRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void writeAppointmentDocToResponse(Long appointmentId, HttpServletResponse response) {
        Appointment appointment = appointmentRepository.findOneById(appointmentId).orElseThrow(
                () -> new IllegalArgumentException("Appointment document with id: " + appointmentId + " not found.")
        );
        writeFileToResponse(appointment.getDoc().getFileInfo(), response);
    }

    @Override
    public void writeImageToResponse(Long imageId, HttpServletResponse response) {
        Image image = imageRepository.findOneById(imageId).orElseThrow(
                () -> new IllegalArgumentException("Image with id: " + imageId + " not found.")
        );
        writeFileToResponse(image.getFileInfo(), response);
    }

    @SneakyThrows
    private void writeFileToResponse(FileInfo fileInfo, HttpServletResponse response) {
        response.setContentType(fileInfo.getType());
        InputStream inputStream = new FileInputStream(new File(fileInfo.getPath()));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
