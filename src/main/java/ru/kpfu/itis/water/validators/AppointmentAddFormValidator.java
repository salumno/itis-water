package ru.kpfu.itis.water.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.water.form.AppointmentAddForm;
import ru.kpfu.itis.water.model.Department;
import ru.kpfu.itis.water.repositories.DepartmentRepository;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AppointmentAddFormValidator implements Validator {

    private DepartmentRepository departmentRepository;

    public AppointmentAddFormValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(AppointmentAddForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        AppointmentAddForm form = (AppointmentAddForm) o;

        Optional<Department> departmentOptional = departmentRepository.findById(form.getDepId());
        if (!departmentOptional.isPresent()) {
            errors.reject("bad.departmentId", "Department with id " + form.getDepId() + " does not exits");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "depId", "invalid.departmentId", "Проверьте идентификатор отдела!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "invalid.description", "Описание причины не заполнено!");

    }
}
