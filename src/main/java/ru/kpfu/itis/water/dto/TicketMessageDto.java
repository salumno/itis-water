package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.TicketMessage;

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
public class TicketMessageDto {

    private Long id;

    private String text;

    private UserDto author;

    private String date;

    private Long ticketId;

    private TicketMessageDto(TicketMessage ticketMessage) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        id = ticketMessage.getId();
        text = ticketMessage.getText();
        author = UserDto.from(ticketMessage.getAuthor());
        date = formatter.format(ticketMessage.getDate());
        ticketId = ticketMessage.getTicket().getId();
    }

    public static TicketMessageDto createOnTicketMessage(TicketMessage ticketMessage) {
        return new TicketMessageDto(ticketMessage);
    }

    public static List<TicketMessageDto> createOnTicketMessages(List<TicketMessage> ticketMessages) {
        return ticketMessages.stream()
                .map(TicketMessageDto::createOnTicketMessage)
                .collect(Collectors.toList());
    }
}
