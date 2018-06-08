package ru.kpfu.itis.water.controllers.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.dto.TicketMessageDto;
import ru.kpfu.itis.water.form.client.ClientTicketMessageAddForm;
import ru.kpfu.itis.water.form.client.TickerDataResponse;
import ru.kpfu.itis.water.services.TicketService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/client/tickets")
public class ClientTicketController {
    private TicketService ticketService;

    public ClientTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("")
    public ResponseEntity<TickerDataResponse> getAllUserTickets(@RequestParam("userId") Long userId) {
        TickerDataResponse ticketDataResponse = new TickerDataResponse(ticketService.getAllTicketsDtoByAuthorId(userId));
        return ResponseEntity.ok(ticketDataResponse);
    }

    @PostMapping("/message")
    public List<TicketMessageDto> addMessageToTicket(@RequestBody ClientTicketMessageAddForm form) {
        ticketService.addTicketMessage(form);
        return ticketService.getAllTicketMessageDto(form.getTicketId());
    }
}
