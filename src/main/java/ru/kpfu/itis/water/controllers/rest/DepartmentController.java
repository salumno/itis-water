package ru.kpfu.itis.water.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.form.DepartmentAddForm;
import ru.kpfu.itis.water.form.DepartmentUpdateForm;
import ru.kpfu.itis.water.model.Department;
import ru.kpfu.itis.water.services.DepartmentService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public List<Department> getDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("")
    public List<Department> addDepartment(@ModelAttribute DepartmentAddForm form) {
        departmentService.addDepartment(form);
        return departmentService.getAllDepartments();
    }

    @PostMapping("/{id}/delete")
    public List<Department> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return departmentService.getAllDepartments();
    }

    @PostMapping("/{id}/update")
    public void updateDepartmentInfo(
            @ModelAttribute DepartmentUpdateForm form,
            @PathVariable("id") Long departmentId) {
        departmentService.updateDepartment(departmentId, form);
    }
}
