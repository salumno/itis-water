package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.Department;
import ru.kpfu.itis.water.model.Employee;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.security.status.UserStatus;

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
public class EmployeeDto {

    private Long id;

    private String surname;

    private String name;

    private String patro;

    private String email;

    private Department department;

    private String inn;

    private Integer salary;

    private String comment;

    private UserStatus status;

    private UserRole role;

    private EmployeeDto(Employee employee) {
        id = employee.getId();
        surname = employee.getSurname();
        name = employee.getName();
        patro = employee.getPatro();
        email = employee.getEmail();
        department = employee.getDepartment();
        inn = employee.getInn();
        salary = employee.getSalary();
        comment = employee.getComment();
        status = employee.getUserData().getStatus();
        role = employee.getUserData().getUserRole();
    }

    public static List<EmployeeDto> from(List<Employee> employees) {
        return employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
    }

    public static EmployeeDto from(Employee employee) {
        return new EmployeeDto(employee);
    }
}
