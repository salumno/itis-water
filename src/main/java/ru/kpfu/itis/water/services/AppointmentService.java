package ru.kpfu.itis.water.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.water.form.AppointmentAddForm;
import ru.kpfu.itis.water.model.Appointment;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface AppointmentService {
    Appointment registerUserToAppointment(AppointmentAddForm departmentId, Authentication authentication);

    void generateDocForAppointment(Long appointmentId);
}
