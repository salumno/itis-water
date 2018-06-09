import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.water.Application;
import ru.kpfu.itis.water.dto.EmployeeDto;
import ru.kpfu.itis.water.model.Employee;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.EmployeeRepository;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.security.status.UserStatus;
import ru.kpfu.itis.water.services.EmployeeService;

import java.util.List;
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
        Employee employee1 = Employee.builder()
                .id(1L)
                .surname("Smith1")
                .name("John1")
                .patro("August1")
                .userData(
                        UserData.builder().id(1L).status(UserStatus.BANNED).userRole(UserRole.USER).build()
                )
                .build();
        Employee employee2 = Employee.builder()
                .id(2L)
                .surname("Smith2")
                .name("John2")
                .patro("August2")
                .userData(
                        UserData.builder().id(2L).status(UserStatus.CONFIRMED).userRole(UserRole.ADMIN).build()
                )
                .build();
        Mockito.when(employeeRepository.findOneById(employee1.getId())).thenReturn(Optional.of(employee1));
        Mockito.when(employeeRepository.findAll()).thenReturn(Lists.newArrayList(employee1, employee2));
    }

    @Test
    public void whenGetEmployeeById_thenReturnEmployee() {
        Long id = 1L;
        Employee found = employeeService.getEmployeeById(id);
        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAllEmployee_thenReturnAllEmployeeList() {
        List<Employee> foundEmployees = employeeService.getAllEmployees();
        assertThat(foundEmployees.size()).isEqualTo(2);
    }

    @Test
    public void whenGetAllDTOEmployee_thenReturnAllEmployeeDTOList() {
        List<EmployeeDto> foundEmployees = employeeService.getAllDTOEmployees();
        assertThat(foundEmployees.size()).isEqualTo(2);
        assertThat(foundEmployees.get(0).getRole()).isEqualTo(UserRole.USER);
    }

    @Test
    public void whenGetEmployeeStatuses_thenReturnArrayOfStatuses() {
        assertThat(employeeService.getEmployeeStatuses()).isEqualTo(UserStatus.values());
    }

    @Test
    public void whenGetEmployeeRoles_thenReturnArrayOfRoles() {
        assertThat(employeeService.getEmployeeRoles()).isEqualTo(UserRole.values());
    }

    @Test
    public void whenGetEmployeeDTOById_thenReturnEmployeeDTO() {
        Long id = 1L;
        EmployeeDto found = employeeService.getEmployeeDTOById(id);
        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetEmployeeDTOByNameFilterValue_thenReturnEmployeeDTOWithNeededName() {
        assertThat(employeeService.getEmployeeDTOByNameFilterValue("john").size()).isEqualTo(2);
        assertThat(employeeService.getEmployeeDTOByNameFilterValue("smith1").size()).isEqualTo(1);
        assertThat(employeeService.getEmployeeDTOByNameFilterValue("b").size()).isEqualTo(0);
        assertThat(employeeService.getEmployeeDTOByNameFilterValue(" ").size()).isEqualTo(2);
    }

}
