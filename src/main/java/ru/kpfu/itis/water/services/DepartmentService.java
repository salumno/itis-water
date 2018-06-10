package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.form.DepartmentAddForm;
import ru.kpfu.itis.water.form.DepartmentUpdateForm;
import ru.kpfu.itis.water.model.Department;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface DepartmentService {
    List<Department> getAllDepartments();

    Department addDepartment(DepartmentAddForm form);

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, DepartmentUpdateForm form);

    Department getDepartmentById(Long departmentId);
}
