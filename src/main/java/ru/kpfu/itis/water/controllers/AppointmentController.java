package ru.kpfu.itis.water.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.water.form.AppointmentAddForm;
import ru.kpfu.itis.water.model.Appointment;
import ru.kpfu.itis.water.services.AppointmentService;
import ru.kpfu.itis.water.services.DepartmentService;
import ru.kpfu.itis.water.validators.AppointmentAddFormValidator;

import javax.validation.Valid;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/user")
public class AppointmentController {
    private DepartmentService departmentService;
    private AppointmentService appointmentService;
    private AppointmentAddFormValidator appointmentAddFormValidator;

    public AppointmentController(DepartmentService departmentService, AppointmentService appointmentService, AppointmentAddFormValidator appointmentAddFormValidator) {
        this.departmentService = departmentService;
        this.appointmentService = appointmentService;
        this.appointmentAddFormValidator = appointmentAddFormValidator;
    }

    @InitBinder("appointmentAddForm")
    public void initAppointmentAddFormValidator(WebDataBinder binder) {
        binder.addValidators(appointmentAddFormValidator);
    }

    @RequestMapping(value = "/appointment-form", method = RequestMethod.GET)
    public String appointmentFormPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "appointment-form-page";
    }


    @RequestMapping(value = "/appointment-form", method = RequestMethod.POST)
    public String appointmentFormSubmit(@Valid @ModelAttribute("appointmentAddForm") AppointmentAddForm form,
                                        Authentication authentication, BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0));
            return "redirect:/user/appointment-form";
        }
        Appointment appointment = appointmentService.registerUserToAppointment(form, authentication);
        attributes.addFlashAttribute("appointment", appointment);
        return "redirect:/user/appointment-form-result";
    }

    @RequestMapping(value = "/appointment-form-result", method = RequestMethod.GET)
    public String appointmentFormPage() {
        return "appointment-form-result-page";
    }

}
