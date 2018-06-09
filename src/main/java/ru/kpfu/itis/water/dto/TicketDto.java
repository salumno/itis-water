package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.Ticket;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TicketDto {
    private Long id;

    private String text;

    private String status;

    private UserDto author;

    private String date;

    private List<TicketMessageDto> messages;

    private TicketDto(Ticket ticket) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        id = ticket.getId();
        text = ticket.getText();
        status = ticket.getStatus().toString();
        author = UserDto.from(ticket.getAuthor());
        date = formatter.format(ticket.getDate());
        messages = TicketMessageDto.createOnTicketMessages(ticket.getMessages());
    }

    public static TicketDto from(Ticket ticket) {
        return new TicketDto(ticket);
    }

    public static List<TicketDto> from(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::from)
                .collect(Collectors.toList());
    }
}
