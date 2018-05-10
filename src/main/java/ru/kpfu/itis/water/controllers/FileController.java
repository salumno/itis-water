package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.services.AppointmentService;
import ru.kpfu.itis.water.services.FileService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
public class FileController {
    private FileService fileService;
    private AppointmentService appointmentService;

    public FileController(FileService fileService, AppointmentService appointmentService) {
        this.fileService = fileService;
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/file/appointment/{id}", method = RequestMethod.GET)
    public void getAppointmentDoc(@PathVariable("id")Long appointmentId, HttpServletResponse response) {
        appointmentService.generateDocForAppointment(appointmentId);
        fileService.writeAppointmentDocToResponse(appointmentId, response);
    }

    @RequestMapping(value = "/file/image/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable("id")Long imageId, HttpServletResponse response) {
        fileService.writeImageToResponse(imageId, response);
    }

}
