package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.services.*;

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
    private NewsService newsService;

    public AdminPageController(TicketService ticketService, AppointmentService appointmentService, EmployeeService employeeService, DepartmentService departmentService, NewsService newsService) {
        this.ticketService = ticketService;
        this.appointmentService = appointmentService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.newsService = newsService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAdminPanelPage() {
        return "admin/panel-page";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsPage() {
        return "admin/news-page";
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public String getSpecificNewsPage(@ModelAttribute("model")ModelMap model, @PathVariable("id") Long newsId) {
        model.addAttribute("news", newsService.getNewsDtoById(newsId));
        return "admin/news-edit-page";
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

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String getDepartmentsPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/departments-page";
    }
}
