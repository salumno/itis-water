package ru.kpfu.itis.water.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.water.form.TicketAddForm;
import ru.kpfu.itis.water.model.Ticket;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

public interface TicketService {
    void addTicket(TicketAddForm ticketAddForm, Authentication authentication);

    Optional<Ticket> getTicketById(Long ticketId);
}
