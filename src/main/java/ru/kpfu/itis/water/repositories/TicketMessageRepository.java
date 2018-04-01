package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.TicketMessage;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TicketMessageRepository extends JpaRepository<TicketMessage, Long> {
    List<TicketMessage> findAllByTicketId(Long ticketId);
}
