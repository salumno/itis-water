package ru.kpfu.itis.water.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.water.form.AppointmentAddForm;
import ru.kpfu.itis.water.model.Appointment;
import ru.kpfu.itis.water.services.AppointmentService;
import ru.kpfu.itis.water.services.DepartmentService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/user")
public class AppointmentController {
    private DepartmentService departmentService;
    private AppointmentService appointmentService;

    public AppointmentController(DepartmentService departmentService, AppointmentService appointmentService) {
        this.departmentService = departmentService;
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/appointment-form", method = RequestMethod.GET)
    public String appointmentFormPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "appointment-form-page";
    }


    @RequestMapping(value = "/appointment-form", method = RequestMethod.POST)
    public String appointmentFormSubmit(@ModelAttribute AppointmentAddForm form,
                                        Authentication authentication, RedirectAttributes attributes) {
        Appointment appointment = appointmentService.registerUserToAppointment(form, authentication);
        attributes.addFlashAttribute("appointment", appointment);
        return "redirect:/user/appointment-form-result";
    }

    @RequestMapping(value = "/appointment-form-result", method = RequestMethod.GET)
    public String appointmentFormPage() {
        return "appointment-form-result-page";
    }

}
