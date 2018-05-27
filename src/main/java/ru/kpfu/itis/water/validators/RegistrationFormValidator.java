package ru.kpfu.itis.water.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.water.form.RegistrationForm;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.repositories.UserDataRepository;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class RegistrationFormValidator implements Validator {

    private UserDataRepository userDataRepository;

    public RegistrationFormValidator(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.getName().equals(aClass.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm form = (RegistrationForm) o;
        Optional<UserData> userDataOptional = userDataRepository.findOneByLogin(form.getLogin());
        if (userDataOptional.isPresent()) {
            errors.reject("bad.login", "Пользователь с логином " + form.getLogin() + " уже существует.");
        }

        if (!form.getPassword().equals(form.getPasswordCheck())) {
            errors.reject("bad.password", "Подтверждение пароля не пройдено.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "invalid.login", "Проверьте Ваш логин.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "invalid.password", "Проверьте Ваш пароль.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name", "Проверьте Ваше имя.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "invalid.surname", "Проверьте Вашу фамилию.");
    }
}
