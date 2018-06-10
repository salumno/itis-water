package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.dto.EmployeeDto;
import ru.kpfu.itis.water.form.EmployeeAddForm;
import ru.kpfu.itis.water.form.EmployeeUpdateForm;
import ru.kpfu.itis.water.model.Employee;
import ru.kpfu.itis.water.model.FileInfo;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.security.status.UserStatus;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee addNewEmployee(EmployeeAddForm form);

    List<EmployeeDto> getAllDTOEmployees();

    Employee updateEmployee(Long employeeId, EmployeeUpdateForm form);

    UserStatus[] getEmployeeStatuses();

    UserRole[] getEmployeeRoles();

    EmployeeDto getEmployeeDTOById(Long employeeId);

    List<EmployeeDto> getEmployeeDTOByNameFilterValue(String filterValue);

    FileInfo generateEmployeesDoc();
}
