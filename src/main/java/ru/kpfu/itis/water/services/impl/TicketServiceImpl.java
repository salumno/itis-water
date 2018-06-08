package ru.kpfu.itis.water.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.dto.TicketDto;
import ru.kpfu.itis.water.dto.TicketMessageDto;
import ru.kpfu.itis.water.form.TicketAddForm;
import ru.kpfu.itis.water.form.TicketMessageAddForm;
import ru.kpfu.itis.water.form.TicketStatusChangeForm;
import ru.kpfu.itis.water.form.TicketUpdateForm;
import ru.kpfu.itis.water.form.client.ClientTicketMessageAddForm;
import ru.kpfu.itis.water.model.*;
import ru.kpfu.itis.water.repositories.TicketMessageRepository;
import ru.kpfu.itis.water.repositories.TicketRepository;
import ru.kpfu.itis.water.repositories.UserRepository;
import ru.kpfu.itis.water.services.TicketService;
import ru.kpfu.itis.water.util.AuthenticationUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class TicketServiceImpl implements TicketService {

    private final String FILTER_UNDEFINED_VALUE = "undefined";

    private TicketRepository ticketRepository;
    private AuthenticationUtil authenticationUtil;
    private TicketMessageRepository ticketMessageRepository;
    private UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, AuthenticationUtil authenticationUtil, TicketMessageRepository ticketMessageRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.authenticationUtil = authenticationUtil;
        this.ticketMessageRepository = ticketMessageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TicketStatus[] getTicketStatuses() {
        return TicketStatus.values();
    }

    @Override
    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findOneById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TicketDto> getAllTicketsDtoByStatus(String currentStatus) {
        if (FILTER_UNDEFINED_VALUE.equals(currentStatus)) {
            return TicketDto.from(getAllTickets());
        }
        TicketStatus status = TicketStatus.valueOf(currentStatus);
        return TicketDto.from(ticketRepository.findAllByStatus(status));
    }

    @Override
    public List<TicketDto> getAllTicketsDtoByAuthorId(Long userId) {
        return ticketRepository.findAllByAuthorId(userId).stream()
                .map(TicketDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketMessage> getAllTicketMessage(Long ticketId) {
        return ticketMessageRepository.findAllByTicketId(ticketId);
    }

    @Override
    public List<TicketMessageDto> getAllTicketMessageDto(Long ticketId) {
        return getAllTicketMessage(ticketId).stream()
                .map(TicketMessageDto::createOnTicketMessage)
                .collect(Collectors.toList());
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
    public void updateTicket(Long ticketId, TicketUpdateForm form) {
        Ticket ticket = ticketRepository.findOneById(ticketId).orElseThrow(
                () -> new IllegalArgumentException("Ticket with id; " + ticketId + " does not exist.")
        );
        ticket.setText(form.getText());
        ticketRepository.save(ticket);
    }

    @Override
    public void changeTicketStatus(TicketStatusChangeForm form) {
        Ticket ticket = ticketRepository.findOneById(form.getTicketId()).orElseThrow(
                () -> new IllegalArgumentException("Ticket with id: " + form.getTicketId() + " not found.")
        );
        TicketStatus newStatus = TicketStatus.valueOf(form.getStatus());
        ticket.setStatus(newStatus);
        ticketRepository.save(ticket);
    }

    @Override
    public void addTicketMessage(TicketMessageAddForm ticketMessageAddForm, Authentication authentication, Long ticketId) {
        User author = authenticationUtil.getUserDataByAuthentication(authentication).getUser();
        Ticket ticket = ticketRepository.findOneById(ticketId).orElseThrow(() -> new IllegalArgumentException("Ticket with ID: " + ticketId + " not found."));
        TicketMessage ticketMessage = TicketMessage.builder()
                .ticket(ticket)
                .author(author)
                .date(Date.valueOf(LocalDate.now()))
                .text(ticketMessageAddForm.getText())
                .build();
        ticketMessageRepository.save(ticketMessage);
    }

    @Override
    public void addTicketMessage(ClientTicketMessageAddForm form) {
        User author = userRepository.findOne(form.getUserId());
        Ticket ticket = ticketRepository.findOneById(form.getTicketId()).orElseThrow(
                () -> new IllegalArgumentException("Ticket with ID: " + form.getTicketId() + " not found.")
        );
        TicketMessage ticketMessage = TicketMessage.builder()
                .ticket(ticket)
                .author(author)
                .date(Date.valueOf(LocalDate.now()))
                .text(form.getMessage())
                .build();
        ticketMessageRepository.save(ticketMessage);
    }
}
