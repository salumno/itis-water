package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.Employee;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findOneById(Long id);
}
