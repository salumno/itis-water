package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.services.AppointmentService;
import ru.kpfu.itis.water.services.DepartmentService;
import ru.kpfu.itis.water.services.EmployeeService;
import ru.kpfu.itis.water.services.TicketService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private TicketService ticketService;
    private AppointmentService appointmentService;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public AdminPageController(TicketService ticketService, AppointmentService appointmentService, EmployeeService employeeService, DepartmentService departmentService) {
        this.ticketService = ticketService;
        this.appointmentService = appointmentService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAdminPanelPage() {
        return "admin/panel-page";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsPage() {
        return "admin/news-page";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getTicketPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        model.addAttribute("statuses", ticketService.getTicketStatuses());
        return "admin/tickets-page";
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getAppointmentsPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "admin/appointments-page";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getEmployeesPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("employees", employeeService.getAllDTOEmployees());
        model.addAttribute("roles", employeeService.getEmployeeRoles());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/employees-page";
    }
}
