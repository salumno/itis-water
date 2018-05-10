package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.AppointmentDoc;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface AppointmentDocRepository extends JpaRepository<AppointmentDoc, Long> {
}
