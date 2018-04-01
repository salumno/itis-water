package ru.kpfu.itis.water.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.water.dto.TicketMessageDto;
import ru.kpfu.itis.water.form.TicketAddForm;
import ru.kpfu.itis.water.form.TicketMessageAddForm;
import ru.kpfu.itis.water.model.Ticket;
import ru.kpfu.itis.water.model.TicketMessage;

import java.util.List;
import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

public interface TicketService {
    void addTicket(TicketAddForm ticketAddForm, Authentication authentication);

    Optional<Ticket> getTicketById(Long ticketId);

    List<Ticket> getAllTickets();

    void addTicketMessage(TicketMessageAddForm ticketMessageAddForm, Authentication authentication, Long ticketId);

    List<TicketMessage> getAllTicketMessage(Long ticketId);

    List<TicketMessageDto> getAllTicketMessageDto(Long ticketId);
}
