package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.services.FileService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/file/appointment-doc/{id}", method = RequestMethod.GET)
    public void getAppointmentDoc(@PathVariable("id")Long appointmentDocId, HttpServletResponse response) {
        fileService.writeAppointmentDocToResponse(appointmentDocId, response);
    }

    @RequestMapping(value = "/file/image/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable("id")Long imageId, HttpServletResponse response) {
        fileService.writeImageToResponse(imageId, response);
    }

}
