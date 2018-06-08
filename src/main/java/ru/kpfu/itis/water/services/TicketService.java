package ru.kpfu.itis.water.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.water.dto.TicketDto;
import ru.kpfu.itis.water.dto.TicketMessageDto;
import ru.kpfu.itis.water.form.TicketAddForm;
import ru.kpfu.itis.water.form.TicketMessageAddForm;
import ru.kpfu.itis.water.form.TicketStatusChangeForm;
import ru.kpfu.itis.water.form.TicketUpdateForm;
import ru.kpfu.itis.water.form.client.ClientTicketMessageAddForm;
import ru.kpfu.itis.water.model.Ticket;
import ru.kpfu.itis.water.model.TicketMessage;
import ru.kpfu.itis.water.model.TicketStatus;

import java.util.List;
import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

public interface TicketService {
    TicketStatus[] getTicketStatuses();

    Optional<Ticket> getTicketById(Long ticketId);

    List<Ticket> getAllTickets();

    List<TicketDto> getAllTicketsDtoByStatus(String currentStatus);

    List<TicketDto> getAllTicketsDtoByAuthorId(Long userId);

    List<TicketMessage> getAllTicketMessage(Long ticketId);

    List<TicketMessageDto> getAllTicketMessageDto(Long ticketId);

    void addTicket(TicketAddForm ticketAddForm, Authentication authentication);

    void updateTicket(Long ticketId, TicketUpdateForm form);

    void changeTicketStatus(TicketStatusChangeForm form);

    void addTicketMessage(TicketMessageAddForm ticketMessageAddForm, Authentication authentication, Long ticketId);

    void addTicketMessage(ClientTicketMessageAddForm form);
}
