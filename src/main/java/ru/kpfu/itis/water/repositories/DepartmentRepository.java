package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.Department;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Optional<Department> findOneById(Long id);
}
