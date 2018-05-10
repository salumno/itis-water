package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.Appointment;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
}
