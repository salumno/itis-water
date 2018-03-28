package ru.kpfu.itis.water.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.form.TicketAddForm;
import ru.kpfu.itis.water.services.TicketService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/user")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getTicketFormPage() {
        return "ticket-form-page";
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
    public String getTicketPage(@PathVariable("id") Long ticketId, @ModelAttribute("model")ModelMap model) {
        model.addAttribute("ticket", ticketService.getTicketById(ticketId));
        return "ticket-page";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public String addTicket(@ModelAttribute TicketAddForm ticketAddForm, Authentication authentication) {
        ticketService.addTicket(ticketAddForm, authentication);
        return "redirect:/user/profile";
    }
}
