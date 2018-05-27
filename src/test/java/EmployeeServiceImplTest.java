import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.water.Application;
import ru.kpfu.itis.water.model.Employee;
import ru.kpfu.itis.water.repositories.EmployeeRepository;
import ru.kpfu.itis.water.services.EmployeeService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        Employee employee = Employee.builder().id(1L).build();
        Mockito.when(employeeRepository.findOneById(employee.getId())).thenReturn(Optional.of(employee));
    }

    @Test
    public void whenGetEmployeeById_thenReturnEmployee() {
        Long id = 1L;
        Employee found = employeeService.getEmployeeById(id);
        assertThat(found.getId()).isEqualTo(id);
    }
}
