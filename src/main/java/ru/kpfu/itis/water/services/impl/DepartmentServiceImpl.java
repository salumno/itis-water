package ru.kpfu.itis.water.services.impl;

import org.springframework.stereotype.Service;
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
}
