package ru.kpfu.itis.water.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.dto.TicketDto;
import ru.kpfu.itis.water.form.TicketStatusChangeForm;
import ru.kpfu.itis.water.model.TicketStatus;
import ru.kpfu.itis.water.services.TicketService;

import java.util.List;

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

    @GetMapping("/status")
    public TicketStatus[] getTicketStatuses() {
        return ticketService.getTicketStatuses();
    }

    @PostMapping("/status/update")
    public void changeTicketStatus(@ModelAttribute TicketStatusChangeForm form) {
        ticketService.changeTicketStatus(form);
    }

    @GetMapping("/status/{value}")
    public List<TicketDto> getTicketsByFilter(@PathVariable("value") String currentStatus) {
        return ticketService.getAllTicketsDtoByStatus(currentStatus);
    }
}
