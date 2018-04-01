package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.TicketMessage;

import java.util.Date;

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
public class TicketMessageDto {

    private Long id;

    private String text;

    private UserDto author;

    private Date date;

    private Long ticketId;

    private TicketMessageDto(TicketMessage ticketMessage) {
        id = ticketMessage.getId();
        text = ticketMessage.getText();
        author = UserDto.createOnTicketMessage(ticketMessage.getAuthor());
        date = ticketMessage.getDate();
        ticketId = ticketMessage.getTicket().getId();
    }

    public static TicketMessageDto createOnTicketMessage(TicketMessage ticketMessage) {
        return new TicketMessageDto(ticketMessage);
    }
}
