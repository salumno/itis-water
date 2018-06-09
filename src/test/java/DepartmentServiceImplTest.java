import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.water.Application;
import ru.kpfu.itis.water.form.DepartmentAddForm;
import ru.kpfu.itis.water.model.Department;
import ru.kpfu.itis.water.repositories.DepartmentRepository;
import ru.kpfu.itis.water.services.DepartmentService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DepartmentServiceImplTest {

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void whenGetAllDepartments_thenReturnListOfDepartments() {
        Department department1 = Department.builder().id(1L).address("address1").name("name1").build();
        Department department2 = Department.builder().id(2L).address("address2").name("name2").build();
        Mockito.when(departmentRepository.findAll()).thenReturn(Lists.newArrayList(department1, department2));

        assertThat(departmentService.getAllDepartments().size()).isEqualTo(2);
    }

    @Test
    public void whenAddDepartment_thenDepartmentReallyAdded() {
        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenAnswer((Answer<Department>) invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null){
                Department addedDepartment = (Department) arguments[0];
                addedDepartment.setId(1L);
                return addedDepartment;
            }
            return null;
        });

        DepartmentAddForm form = new DepartmentAddForm("name", "address");
        Department added = departmentService.addDepartment(form);

        assertThat(added.getId()).isNotNull();
    }

    @Test
    public void whenDepartmentById_thenReturnDepartmentWithRequestedId() {
        Department department = Department.builder().id(1L).address("address").name("name").build();
        Mockito.when(departmentRepository.findOneById(department.getId())).thenReturn(Optional.of(department));
        Long departmentId = 1L;
        Department found = departmentService.getDepartmentById(departmentId);

        assertThat(found.getId()).isEqualTo(departmentId);
    }
}
