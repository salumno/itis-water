package ru.kpfu.itis.water.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.form.TicketAddForm;
import ru.kpfu.itis.water.model.Ticket;
import ru.kpfu.itis.water.model.TicketStatus;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.TicketRepository;
import ru.kpfu.itis.water.services.TicketService;
import ru.kpfu.itis.water.util.AuthenticationUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private AuthenticationUtil authenticationUtil;

    public TicketServiceImpl(TicketRepository ticketRepository, AuthenticationUtil authenticationUtil) {
        this.ticketRepository = ticketRepository;
        this.authenticationUtil = authenticationUtil;
    }

    @Override
    public void addTicket(TicketAddForm ticketAddForm, Authentication authentication) {
        UserData userData = authenticationUtil.getUserDataByAuthentication(authentication);
        Ticket ticket = Ticket.builder()
                .date(Date.valueOf(LocalDate.now()))
                .messages(Collections.emptyList())
                .author(userData.getUser())
                .status(TicketStatus.NOT_VIEWED)
                .text(ticketAddForm.getText())
                .build();
        ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
