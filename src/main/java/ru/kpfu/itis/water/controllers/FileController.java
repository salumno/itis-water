package ru.kpfu.itis.water.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.model.FileInfo;
import ru.kpfu.itis.water.services.AppointmentService;
import ru.kpfu.itis.water.services.EmployeeService;
import ru.kpfu.itis.water.services.FileService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/file")
public class FileController {
    private FileService fileService;
    private AppointmentService appointmentService;
    private EmployeeService employeeService;

    public FileController(FileService fileService, AppointmentService appointmentService, EmployeeService employeeService) {
        this.fileService = fileService;
        this.appointmentService = appointmentService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/appointment/{id}", method = RequestMethod.GET)
    public void getAppointmentDoc(@PathVariable("id")Long appointmentId, HttpServletResponse response) {
        appointmentService.generateDocForAppointment(appointmentId);
        fileService.writeAppointmentDocToResponse(appointmentId, response);
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable("id")Long imageId, HttpServletResponse response) {
        fileService.writeImageToResponse(imageId, response);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public void getEmployees(HttpServletResponse response) {
        FileInfo employeeDocumentInfo = employeeService.generateEmployeesDoc();
        fileService.writeEmployeesToResponse(employeeDocumentInfo, response);
    }

}
