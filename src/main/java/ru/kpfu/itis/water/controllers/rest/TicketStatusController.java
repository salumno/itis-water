package ru.kpfu.itis.water.controllers.rest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.water.form.TicketStatusChangeForm;
import ru.kpfu.itis.water.services.TicketService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/admin/tickets")
public class TicketStatusController {

    private TicketService ticketService;

    public TicketStatusController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/status")
    public void changeTicketStatus(@ModelAttribute TicketStatusChangeForm form) {
        ticketService.changeTicketStatus(form);
    }
}
