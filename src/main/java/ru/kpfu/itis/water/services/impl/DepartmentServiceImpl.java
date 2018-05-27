package ru.kpfu.itis.water.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.form.DepartmentAddForm;
import ru.kpfu.itis.water.form.DepartmentUpdateForm;
import ru.kpfu.itis.water.model.Department;
import ru.kpfu.itis.water.repositories.DepartmentRepository;
import ru.kpfu.itis.water.services.DepartmentService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void addDepartment(DepartmentAddForm form) {
        Department department = Department.builder()
                .name(form.getName())
                .address(form.getAddress())
                .build();
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.delete(departmentId);
    }

    @Override
    public void updateDepartment(Long departmentId, DepartmentUpdateForm form) {
        Department department = getDepartmentById(departmentId);
        department.setAddress(form.getUpdatedAddress());
        department.setName(form.getUpdatedName());
        departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findOneById(departmentId).orElseThrow(
                () -> new IllegalArgumentException("Department with id " + departmentId + " does not exist.")
        );
    }
}
