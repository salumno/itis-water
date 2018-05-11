package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.dto.EmployeeDto;
import ru.kpfu.itis.water.form.EmployeeAddForm;
import ru.kpfu.itis.water.form.EmployeeUpdateForm;
import ru.kpfu.itis.water.model.Employee;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void addNewEmployee(EmployeeAddForm form);

    List<EmployeeDto> getAllDTOEmployees();

    void updateEmployee(Long employeeId, EmployeeUpdateForm form);
}
