package ru.kpfu.itis.water.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.dto.TicketMessageDto;
import ru.kpfu.itis.water.form.TicketMessageAddForm;
import ru.kpfu.itis.water.services.TicketService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/tickets")
public class TicketMessageRestController {

    private TicketService ticketService;

    public TicketMessageRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/{ticketId}/message", method = RequestMethod.GET)
    public List<TicketMessageDto> addTicketMessage(@PathVariable("ticketId") Long ticketId) {
        return ticketService.getAllTicketMessageDto(ticketId);
    }

    @RequestMapping(value = "/{ticketId}/message", method = RequestMethod.POST)
    public List<TicketMessageDto> addTicketMessage(@ModelAttribute TicketMessageAddForm ticketMessageAddForm,
                                                Authentication authentication,
                                                @PathVariable("ticketId") Long ticketId
    ) {
        ticketService.addTicketMessage(ticketMessageAddForm, authentication, ticketId);
        return ticketService.getAllTicketMessageDto(ticketId);
    }
}
