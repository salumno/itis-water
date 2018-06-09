package ru.kpfu.itis.water.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.form.TicketUpdateForm;
import ru.kpfu.itis.water.services.TicketService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{id}/update")
    public void updateTicketText(@ModelAttribute TicketUpdateForm form, @PathVariable("id") Long ticketId) {
        ticketService.updateTicket(ticketId, form);
    }
}
