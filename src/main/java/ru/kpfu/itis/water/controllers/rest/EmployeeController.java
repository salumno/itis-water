package ru.kpfu.itis.water.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.dto.EmployeeDto;
import ru.kpfu.itis.water.form.EmployeeAddForm;
import ru.kpfu.itis.water.form.EmployeeUpdateForm;
import ru.kpfu.itis.water.services.EmployeeService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/admin/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<EmployeeDto> addEmployee(@ModelAttribute EmployeeAddForm form) {
        employeeService.addNewEmployee(form);
        return employeeService.getAllDTOEmployees();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<EmployeeDto> getEmployees() {
        return employeeService.getAllDTOEmployees();
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public List<EmployeeDto> updateEmployee(@ModelAttribute EmployeeUpdateForm form, @PathVariable("id") Long employeeId) {
        employeeService.updateEmployee(employeeId, form);
        return employeeService.getAllDTOEmployees();
    }
}
