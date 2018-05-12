package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.Ticket;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findOneById(Long id);
}
