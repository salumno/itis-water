import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.water.Application;
import ru.kpfu.itis.water.model.Appointment;
import ru.kpfu.itis.water.repositories.AppointmentRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void whenFindOneById_thenReturnAppointmentOptional() {
        Appointment appointment = Appointment.builder().build();
        testEntityManager.persist(appointment);
        testEntityManager.flush();

        Optional<Appointment> found = appointmentRepository.findOneById(appointment.getId());

        assertThat(found.isPresent()).isEqualTo(true);
    }
}
