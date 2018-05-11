package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.services.TicketService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private TicketService ticketService;

    public AdminPageController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsPage() {
        return "admin/news-page";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getTicketPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        model.addAttribute("statuses", ticketService.getTicketStatuses());
        return "admin/ticket-page";
    }
}
